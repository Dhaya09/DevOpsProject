pipeline {
    agent any

    environment {
        BUILD_PATH = "${env.WORKSPACE}"
        BUILD_NUMBER = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('📦 Build') {
            steps {
                dir('ansible_project') {
                    sh 'ansible-playbook -i inventory site.yml --tags build --extra-vars "build_path=$BUILD_PATH build_number=$BUILD_NUMBER"'
                }
            }
        }

        stage('🧪 Test') {
            steps {
                dir('ansible_project') {
                    sh 'ansible-playbook -i inventory site.yml --tags test --extra-vars "build_path=$BUILD_PATH build_number=$BUILD_NUMBER"'
                }
            }
        }

        stage('🐳 Dockerize') {
            steps {
                dir('ansible_project') {
                    sh 'ansible-playbook -i inventory site.yml --tags dockerize --extra-vars "build_path=$BUILD_PATH build_number=$BUILD_NUMBER"'
                }
            }
        }

        stage('🚀 Deploy') {
            steps {
                dir('ansible_project') {
                    sh 'ansible-playbook -i inventory site.yml --tags deploy --extra-vars "build_path=$BUILD_PATH build_number=$BUILD_NUMBER"'
                }
            }
        }

        stage('📊 Monitor') {
            steps {
                dir('ansible_project') {
                    sh 'ansible-playbook -i inventory site.yml --tags monitor --extra-vars "build_path=$BUILD_PATH build_number=$BUILD_NUMBER"'
                }
            }
        }

        stage('🔁 Rollback (if needed)') {
            steps {
                dir('ansible_project') {
                    sh 'ansible-playbook -i inventory site.yml --tags rollback --extra-vars "build_path=$BUILD_PATH build_number=$BUILD_NUMBER"'
                }
            }
        }
    }
}

