pipeline {
  agent any
  stages {
    stage('Check and Prepare') {
      steps {
        sh 'rm -f src/main/resources/application.properties'
        sh 'cp /home/deploy-props/Hblog/application.properties src/main/resources/application.properties'
        sh 'rm -f src/main/resources/static/props/base.properties'
        sh 'cp /home/deploy-props/Hblog/base.properties src/main/resources/static/props/base.properties'
        sh 'gradle check -x build -x test --stacktrace'
      }
    }
    
    stage('Test') {
      steps {
        sh '(gradle test --stacktrace) || true'
        junit 'build/test-results/*.xml'
        sh 'rm -f -r test-arch'
        sh 'mkdir test-arch'
        sh 'zip -r test-arch/test-report.zip build/reports'
        archiveArtifacts 'test-arch/*.zip'
      }
    }
    
    stage('Build') {
      steps {
        sh 'gradle build --stacktrace'
      }
    }
    
    stage('Artifacts') {
      steps {
        archiveArtifacts(artifacts: 'build/libs/*', allowEmptyArchive: true, onlyIfSuccessful: true)
      }
    }
    
    stage('Clean') {
      steps {
        sh '(pkill -f gradle) || true'
      }
    }
    
    stage('Deploy') {
      steps {
        sh 'rm -f /home/Programs/Hblog/out/HBlog-0.0.1.jar'
        sh 'cp build/libs/HBlog-0.0.1.jar /home/Programs/Hblog/out/HBlog-0.0.1.jar'
        sh 'chmod a+x /home/Programs/Hblog/out/HBlog-0.0.1.jar'
        withEnv(overrides: ['JENKINS_NODE_COOKIE=dontKillMe']) {
          sh '''# cd /home/Programs/Hblog/out/ && nohup java -jar HBlog-0.0.1.jar > hblog.log 2>&1 &
cd /home/Programs/Hblog/out/ && nohup ./hblog_bg.sh &'''
        }
        
      }
    }
  }
}
