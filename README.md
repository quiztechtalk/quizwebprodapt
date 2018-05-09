
Environments : 


Git : 2.17.0 or any latest version

	  (https://git-scm.com/download/win)
	  
IDE : Eclipse luna or any latest version

	 (https://www.eclipse.org/downloads/packages/release/luna/sr2)

Java : 1.8 

	  (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
Maven :  3.5.3 

      (https://maven.apache.org/download.cgi)

Spring Boot : 2.0.1

Heroku cli : any latest version
			
Swagger : 2.0

Jwt : 3.3.0

........................................................................................................

Project Step Up and execute Quiz web services

Step 1 : Sign Up with free trial

Step 2 : download and install using following url

		 https://devcenter.heroku.com/articles/heroku-cli
		 
step 3 : create a new app using heroku dashboard

		 https://dashboard.heroku.com/  like "quizwebprodapt"
		 
step 4 : create a web project "quizwebprodapt" using maven in eclipse or any other tool

		 (https://github.com/quiztechtalk/quizwebprodapt.git)
		 
step 5 : add Procfile in project its indicate the runtime script in heroku app

setp 6 : make clean and install using maven
		 
step 7 : once set up done need to push the changes in heroku app

step 8 : go to project work space location using command prompt 

step 9 : run and build in heroku cloud using following command

		$ heroku login   					          	# using with heroku login credentials 
		$ git init       						          # initialize heroku git repository 
		$ heroku git:remote -a quizwebprodapt	# initialize the project 
		$ git add .								            # add the project
		$ git commit -am "make it better"	  	# commit the changes
		$ git push heroku master 				      # push the the commit changes into heroku git master
		$ heroku open							            # open the project in browser
		
Step 10 : run the quiz web using with swagger tools 
		  https://app.swaggerhub.com/ sign up using credentials
		  upload or develop the swagger json
		  (https://app.swaggerhub.com/apis/Prodapt3/quizwebprodapt/1.3.0)


step 11 : /createToken
			create the jwt token using with user createToken
			{
				"name": "testuser",
				"password": "testuser@123"
			}
			once token created verify with /verifyToken services 
			then put the token in Authorise Header 
			
step 12 : then execute the quiz web services

			/getTopics --get
      
			/startQuizForUser/{topic} --get
      
			/getNextQuestion/{userQuizId} --get
      
			/submitUserResponce --post
      
			/endQuizForUse  --post

			
			





