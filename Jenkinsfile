pipeline {
  agent any
  stages {
    stage('Check and Prepare') {
      steps {
        sh 'rm -f src/main/resources/application.properties'
        sh 'cp /home/deploy-props/Hblog/application.properties src/main/resources/application.properties'
        sh 'gradle check -x build -x test --stacktrace'
      }
    }
    stage('Build') {
      steps {
        sh 'gradle build -x test --stacktrace'
      }
    }
    stage('Test') {
      steps {
        sh 'gradle test --stacktrace'
      }
    }
    stage('Prepare results') {
      parallel {
        stage('Tests') {
          steps {
            junit(allowEmptyResults: true, testResults: 'build/reports/tests/*')
            junit(testResults: 'build/test-results/*', allowEmptyResults: true)
          }
        }
        stage('Artifacts') {
          steps {
            archiveArtifacts(artifacts: 'build/libs/*', allowEmptyArchive: true, onlyIfSuccessful: true)
          }
        }
      }
    }
    stage('Deploy') {
      steps {
        sh 'pkill -f Hblog || true'
        sh 'rm -f /home/Programs/Hblog/out/HBlog-0.0.1.jar'
        sh 'cp build/libs/HBlog-0.0.1.jar /home/Programs/Hblog/out/HBlog-0.0.1.jar'
        sh 'chmod a+x /home/Programs/Hblog/out/HBlog-0.0.1.jar'
        sh 'cd /home/Programs/Hblog/out/ && ./hblog_bg.sh'
        
          withEnv(['BUILD_ID=dontkill']) {
            sh 'cd /home/Programs/Hblog/out/ && ./hblog_bg.sh'
          }
        
      }
    }
    stage('Clean') {
      steps {
        sh 'pkill -f gradle || true'
      }
    }
  }
}
