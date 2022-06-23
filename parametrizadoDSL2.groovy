job('ejemplo3-job-DSL') {
	description('Job DSL de ejemplo para el curso de Jenkins')
  	scm {
      		git('https://github.com/AndyVillegas/jenkins.job.parametrizado.git', 'main') { node ->
        		node / gitConfigName('AndyVillegas')
        		node / gitConfigEmail('andyvillegas440@gmail.com')
      		}
    	} 
  	parameters {
   		stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job Booleano')
      		choiceParam('planeta', ['Mercurio', 'Venus', 'Tierrra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
      		booleanParam('agente', false)
    	}
  	triggers {
    		cron('H/7 * * * *')
        	githubPush()
    	}
  	steps {
    		shell("bash jobscript.sh")
    	}
  	publishers {
      		mailer('andyvillegas440@gmail.com', true, true)
      		slackNotifier {
		  notifyAborted(true)
		  notifyEveryFailure(true)
		  notifyNotBuilt(false)
		  notifyUnstable(false)
		  notifyBackToNormal(true)
		  notifySuccess(false)
		  notifyRepeatedFailure(false)
		  startNotification(false)
		  includeTestSummary(false)
		  includeCustomMessage(false)
		  customMessage(null)
		  sendAs(null)
		  commitInfoChoice('NONE')
		  teamDomain(null)
		  authToken(null)
        	}
    	}
}
