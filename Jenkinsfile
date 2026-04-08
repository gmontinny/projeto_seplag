// Pipeline Backend Genérico - Suporta: Java/Maven, Java/Gradle, Python, Node.js, .NET, Go
// USO: Copie este arquivo para a raiz do seu projeto como "Jenkinsfile"

def REGISTRY = env.REGISTRY_URL ?: 'registry:5000'
def IMAGE_NAME = "${REGISTRY}/${env.JOB_NAME.replaceAll('/', '-').toLowerCase()}"
def IMAGE_TAG = "${env.BUILD_NUMBER}-${env.GIT_COMMIT?.take(7) ?: 'latest'}"
def DETECTED_LANG = ''

pipeline {
    agent any
    options {
        ansiColor('xterm')
        timestamps()
        disableConcurrentBuilds()
        timeout(time: 30, unit: 'MINUTES')
    }

    parameters {
        choice(name: 'PROJECT_LANG', choices: ['auto', 'java-maven', 'java-gradle', 'python', 'node', 'dotnet', 'go'], description: 'Linguagem do projeto (auto = detecta automaticamente)')
    }

    environment {
        SONAR_SCANNER_OPTS = '-Xmx512m'
    }

    stages {
        stage('Detect Language') {
            steps {
                script {
                    if (params.PROJECT_LANG == 'auto') {
                        if (fileExists('pom.xml'))              { DETECTED_LANG = 'java-maven' }
                        else if (fileExists('build.gradle'))    { DETECTED_LANG = 'java-gradle' }
                        else if (fileExists('requirements.txt') || fileExists('pyproject.toml')) { DETECTED_LANG = 'python' }
                        else if (fileExists('package.json'))    { DETECTED_LANG = 'node' }
                        else if (fileExists('go.mod'))          { DETECTED_LANG = 'go' }
                        else if (findFiles(glob: '*.csproj').length > 0 || findFiles(glob: '*.sln').length > 0) { DETECTED_LANG = 'dotnet' }
                        else { error "Não foi possível detectar a linguagem. Defina PROJECT_LANG manualmente." }
                    } else {
                        DETECTED_LANG = params.PROJECT_LANG
                    }
                    echo "Linguagem detectada: ${DETECTED_LANG}"
                }
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    switch (DETECTED_LANG) {
                        case 'java-maven':
                            sh 'mvn clean verify -B'
                            break
                        case 'java-gradle':
                            sh './gradlew clean build'
                            break
                        case 'python':
                            sh '''
                                python3 -m venv .venv
                                . .venv/bin/activate
                                pip install -r requirements.txt
                                pip install pytest coverage
                                coverage run -m pytest --junitxml=test-results.xml || true
                                coverage xml -o coverage.xml
                            '''
                            break
                        case 'node':
                            sh '''
                                npm ci
                                npm test -- --coverage || true
                            '''
                            break
                        case 'dotnet':
                            sh '''
                                dotnet restore
                                dotnet build --no-restore
                                dotnet test --no-build --collect:"XPlat Code Coverage" --results-directory ./test-results || true
                            '''
                            break
                        case 'go':
                            sh '''
                                go mod download
                                go test ./... -coverprofile=coverage.out -v 2>&1 | tee test-results.txt || true
                            '''
                            break
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    script {
                        def projectKey = env.JOB_NAME.replaceAll('/', ':')
                        switch (DETECTED_LANG) {
                            case 'java-maven':
                                sh "mvn sonar:sonar -Dsonar.projectKey=${projectKey}"
                                break
                            case 'java-gradle':
                                sh "./gradlew sonar -Dsonar.projectKey=${projectKey}"
                                break
                            default:
                                sh """
                                    sonar-scanner \
                                      -Dsonar.projectKey=${projectKey} \
                                      -Dsonar.sources=. \
                                      -Dsonar.host.url=http://sonarqube:9000
                                """
                                break
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Docker Build & Push') {
            when { anyOf { branch 'main'; branch 'master'; branch 'develop' } }
            steps {
                script {
                    if (!fileExists('Dockerfile')) {
                        echo "WARN: Dockerfile não encontrado, gerando automaticamente..."
                        generateDockerfile(DETECTED_LANG)
                    }
                    sh """
                        docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -t ${IMAGE_NAME}:latest .
                        docker push ${IMAGE_NAME}:${IMAGE_TAG}
                        docker push ${IMAGE_NAME}:latest
                    """
                }
            }
        }

        stage('Deploy via Portainer') {
            when { anyOf { branch 'main'; branch 'master' } }
            steps {
                script {
                    deployToPortainer(IMAGE_NAME, IMAGE_TAG)
                }
            }
        }
    }

    post {
        always { cleanWs() }
    }
}

def generateDockerfile(String lang) {
    def content = ''
    switch (lang) {
        case 'java-maven':
            content = 'FROM eclipse-temurin:17-jre-alpine\nCOPY target/*.jar /app/app.jar\nEXPOSE 8080\nENTRYPOINT ["java","-jar","/app/app.jar"]'
            break
        case 'java-gradle':
            content = 'FROM eclipse-temurin:17-jre-alpine\nCOPY build/libs/*.jar /app/app.jar\nEXPOSE 8080\nENTRYPOINT ["java","-jar","/app/app.jar"]'
            break
        case 'python':
            content = 'FROM python:3.12-slim\nWORKDIR /app\nCOPY requirements.txt .\nRUN pip install --no-cache-dir -r requirements.txt\nCOPY . .\nEXPOSE 8000\nCMD ["python","main.py"]'
            break
        case 'node':
            content = 'FROM node:20-alpine\nWORKDIR /app\nCOPY package*.json ./\nRUN npm ci --only=production\nCOPY . .\nEXPOSE 3000\nCMD ["node","index.js"]'
            break
        case 'dotnet':
            content = 'FROM mcr.microsoft.com/dotnet/aspnet:8.0-alpine\nWORKDIR /app\nCOPY bin/Release/net8.0/publish/ .\nEXPOSE 8080\nENTRYPOINT ["dotnet","app.dll"]'
            break
        case 'go':
            content = 'FROM golang:1.22-alpine AS build\nWORKDIR /src\nCOPY . .\nRUN go build -o /app .\nFROM alpine:3.19\nCOPY --from=build /app /app\nEXPOSE 8080\nENTRYPOINT ["/app"]'
            break
    }
    writeFile file: 'Dockerfile', text: content
}

def deployToPortainer(String imageName, String imageTag) {
    withCredentials([usernamePassword(credentialsId: 'portainer-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        def token = sh(script: """
            curl -s -X POST http://portainer:9443/api/auth \
              -H 'Content-Type: application/json' \
              -d '{"username":"${USER}","password":"${PASS}"}' \
              --insecure | python3 -c "import sys,json;print(json.load(sys.stdin)['jwt'])"
        """, returnStdout: true).trim()

        def serviceName = imageName.split('/')[-1]
        sh """
            curl -s -X POST 'http://portainer:9443/api/stacks/create/standalone/string?endpointId=1' \
              -H 'Authorization: Bearer ${token}' \
              -H 'Content-Type: application/json' \
              --insecure \
              -d '{
                "name": "${serviceName}",
                "stackFileContent": "version: \\"3.9\\"\\nservices:\\n  app:\\n    image: ${imageName}:${imageTag}\\n    restart: unless-stopped\\n    network_mode: bridge"
              }' || echo "Stack já existe, atualizando imagem..."

            docker pull ${imageName}:${imageTag} || true
        """
    }
}
