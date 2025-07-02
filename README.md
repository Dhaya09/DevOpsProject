DevOps Project
MoneyMetrics – The Financial Calculator

Name : Dhayanidhi S
Reg no : 23BIT0214
Email : dhayaisro09@gmail.com


Project Statement : 
'Create a complete DevOps CI/CD Pipeline with Jenkins for a Use Case (different from the Calculator application) by using GIT, Maven, Puppet/Ansible/Terraform, Docker, JUnit, Graphite, and Grafana. You can add more tools if you want.'



Overview :
This project automates the complete software delivery lifecycle—including build, test, dockerization, deployment, monitoring, and rollback—for a CLI-based Java Financial Calculator. The pipeline is triggered automatically using SCM polling in Jenkins, which checks for new commits every 2 minutes and initiates a build only if changes are detected. Jenkins orchestrates the entire process, while Ansible executes modular roles defined in a centralized playbook to carry out tasks such as Maven-based compilation, JUnit testing, Docker image creation, and deployment to a Kubernetes (Minikube) cluster. Real-time monitoring is implemented using Collectd (for data collection), Graphite (for time-series storage), and Grafana (for visualization dashboards). Additionally, an automatic rollback mechanism is built into the pipeline to revert to the last successful version if any stage fails. This tightly integrated setup ensures fast, reliable, and fully automated software delivery with minimal manual intervention.
📚 Table of Contents
________________________________________
S.No.	Topic	Page No.
1	About Project and Its Components	3
2	Use Case	7
3	Project	
3.1	Creating Project Repository	8
3.2	Setting Up Maven Project	9
3.3	Docker Setup and Dockerization	16
3.4	JUnit Testing	18
3.5	Deploying on Kubernetes with Minikube	20
3.6	Monitoring Using Grafana and Graphite	25
3.7	Ansible Orchestration and Automation	28
3.8	CI/CD Using Jenkins	32
3.8.1	Freestyle Project	34
3.8.2	Running Only Ansible Playbook in Jenkins	38
3.8.3	Pipeline with Ansible Integration	40
3.9	Git Polling and Build Trigger Demonstration	44
3.10	Updating Playbook and Roles for Pipeline Functionality	45
3.11	Rollback Implementation	46
3.12	Migration from Local Git Repository to GitHub	48
4	Conclusion 	49
5	Codes ( Github Link )	49
________________________________________


🛠️ Project Components

1. 🔢 Java Financial Calculator
•	A CLI-based application developed in Java.
•	Accepts user input via terminal.
•	Computes interest-related financial values.
2. ✅ JUnit Test Cases
•	Unit tests verify all financial functions.
•	Test failure is intentionally triggered to simulate rollback.
3. 🧪 Maven Build & Test
•	Jenkins runs
	mvn clean package

•	If any JUnit test fails, the pipeline does not stop immediately. Instead, Ansible marks a flag for rollback.
________________________________________
🐳 Docker Structure
•	The Dockerfile builds a Java app image:
Dockerfile
•	Two image tags maintained:
o	latest
o	previous (used for rollback)
________________________________________
📜 Jenkins CI/CD Pipeline Stages
1.	SCM Checkout
o	Pulls the latest code from your Git repo (local path or GitHub).
o	SCM Polling or Webhook triggers pipeline automatically on code push.
2.	Build
o	Compiles code using mvn package.
3.	Test
o	Runs JUnit tests.
o	If any test fails, Ansible creates a rollback trigger file.
4.	Dockerize
o	Copies Dockerfile to workspace.
o	Builds a Docker image tagged with build number (e.g., financial-calc:6) and latest.
o	Tags previous image as previous for rollback.
5.	Deploy
o	Removes any running financial-calc container.
o	Runs the new image.
o	Waits for 5 seconds to allow startup.
6.	Monitor
o	Starts Grafana + Graphite stack using Docker Compose.
o	Visualization of CPU, memory, app logs, etc.
7.	Rollback (if needed)
o	If rollback_trigger file is detected, rollback role is executed.
o	Removes broken container.
o	Replaces with image tagged as previous.
________________________________________
📦 Ansible Roles
The project has modular Ansible roles:
Role	Purpose
build	Maven build and packaging
test	Run JUnit tests and create rollback trigger on failure
dockerize	Build Docker image and tag it
deploy	Remove old container and deploy new one
monitor	Start Grafana and Graphite with Docker Compose
rollback	Check for test failure and perform image rollback
All roles are included via site.yml  master playbook with tag-based execution.
________________________________________
📊 Monitoring Stack
Tools:
•	Collectd – Gathers metrics.
•	Graphite – Time-series database.
•	Grafana – Visual dashboards.
Setup:
•	Runs via Docker Compose (monitor/docker-compose.yml).
•	Started through Ansible during the monitor role.
________________________________________
🔁 Rollback Workflow
Condition	Action
Tests and Build pass	Continue with deployment
Tests or Build fail	- Create rollback_trigger file 
- Rollback role triggers
Rollback	Remove new image
run previously working image
Rollback is fully automated and resilient.
________________________________________
🔐 Permissions & Ownership
•	Git repo is made a safe directory using:
git config --global --add safe.directory /path/to/repo
•	Ownership is managed so both jenkins and dhayanidhi-s users can access files and Docker socket.
________________________________________
⏱️ SCM Polling vs Webhook
•	SCM Polling is configured with * * * * * to check every minute.
•	You can eliminate polling delay by using a GitHub webhook to notify Jenkins instantly on push.
________________________________________
✅ Success Criteria
•	Jenkins automatically detects code push.
•	If build or test fails, rollback is successful.
•	Docker container always reflects last known working build.
•	Metrics are viewable in Grafana dashboard.
________________________________________
📦 Directory Structure (Simplified)

project_root/
├── ansible_project/
│   ├── roles/
│   │   ├── build/
│   │   ├── test/
│   │   ├── dockerize/
│   │   ├── deploy/
│   │   ├── monitor/
│   │   └── rollback/
│   ├── inventory
│   ├── site.yml
├── Dockerfile
├── src/
├── target/
├── Jenkinsfile
├── Jar File
└── pom.xml


________________________________________
📈 Learning Outcomes
•	Understand CI/CD fundamentals using Jenkins.
•	Build Ansible playbooks and roles for automation.
•	Learn Docker image creation and container deployment.
•	Automate rollback mechanisms using Ansible.
•	Integrate monitoring into a DevOps pipeline using Grafana.
•	Hands-on experience in connecting all DevOps stages into a seamless workflow.
________________________________________
🧠 Possible Future Enhancements
•	Convert Java CLI app into a REST API using Spring Boot.
•	Use GitHub Webhooks instead of SCM polling for real-time trigger.
•	Integrate Slack/Email notifications for pipeline events.
•	Add database integration for persistent data storage.
•	Deploy in Kubernetes (Minikube or cloud-managed) for scalability

________________________________________


💡 USE CASE 

The Financial Calculator Project
The Financial Calculator is a comprehensive Java-based application designed to perform a wide range of financial computations that are commonly needed by individuals, accountants, businesses, students, and financial analysts. It serves as a powerful command-line tool that simplifies complex mathematical financial operations by accepting user inputs and providing accurate results within seconds. The calculator is built with clean, modular Java code and offers a reliable, easy-to-use interface, making it suitable for both educational purposes and real-world financial decision-making.

🔢 Key Functionalities (Performs 10+ Operations ):
1.	Simple Interest (SI): Calculates interest based on principal, rate, and time.
2.	Compound Interest (CI): Computes interest on principal + accumulated interest over time.
3.	EMI (Equated Monthly Installment): Calculates fixed monthly payment for a loan.
4.	Future Value (FV): Determines the value of an investment after a period at a fixed interest rate.
5.	Present Value (PV): Calculates today's value of a future amount of money.
6.	Loan Affordability Check: Computes maximum loan based on EMI and interest rate.
7.	Savings Growth Estimation: Predicts savings over time with periodic deposits.
8.	Investment Return Analysis: Calculates returns from various investment scenarios.
9.	Break-even Point Estimation: Determines when an investment will break even.
10.	Interest Rate Conversion: Converts annual interest rates to monthly or daily and vice versa.

🎯 Real-World Use Cases & Benefits
This calculator is especially useful for bank employees, loan officers, investors, financial advisors, students studying commerce or finance, and individuals managing personal finances. It allows quick assessments of loans, savings, and investments, making budgeting and financial planning more efficient. For example, a student can estimate how much they’ll owe after taking a student loan, or a home buyer can compute EMI options before approaching a bank. Businesses can evaluate investment proposals or compare loan options to select the most cost-effective one.
The Financial Calculator eliminates manual errors, saves time, and promotes financial literacy by helping users understand how financial parameters interact — making it a vital tool in day-to-day financial decision-making.



📦 Creating project repository

Install GIT client and server on the same host . 
ssh git@localhost – Establishes a secure SSH connection to the local Git server for performing Git operations.
git init --bare project_repository.git – Creates a bare repository to act as the central remote for code versioning and CI/CD integration.
Bare repositories store only version history and are ideal for collaborative or automated workflows (no working directory).
git clone git@localhost:project_repository.git – Clones the bare repo into a working directory for development or Jenkins builds.
This setup is essential for pushing code, polling for changes, and triggering the DevOps pipeline automatically














 
Project_repository created .
⚗️Setting Up Maven Project
Creating a modular Maven project that will later be used in the CI/CD pipeline.

🛠️ 1. Installed Java JDK (Prerequisite for Eclipse)
📥 2. Downloaded and Installed Eclipse IDE
⚙️ 3. Configuring Eclipse Environment
•	Ensuring Eclipse is using the correct JDK via:
Window → Preferences → Java → Installed JREs
•	Selecting the path to OpenJDK 17.
 

________________________________________

📦 4. Creating a Maven Project in Eclipse
•	File → New → Project → Maven → Maven Project
•	Choose archetype, like maven-archetype-quickstart
•	Fill in:
o	Group ID: com.devops
o	Artifact ID: devopsProject
•	Click Finish to create the project structure.

 
 
 

 
 
 
🧮 5. Developing Financial Calculator App
•	Inside src/main/java/com.devops.devopsProject, create ProjectMain.java with logic for:
o	Simple Interest , Compound Interest , EMI , Net Interest Earned and more as needed.
o	Accept command-line arguments and log output using Log4j
o	Used java as language
 ________________________________________
📄 6.  Configuring pom.xml
Add Required Maven Dependencies which Include:
•	junit:junit:4.12 for testing
•	log4j:log4j:1.2.17 for logging
•	maven-assembly-plugin for creating a JAR with dependencies
 
________________________________________
⚗️ 7. Building and Package the App
•	Configure Build path
•	Right-click project → Run As → Maven clean
•	Then Run As → Maven install
•	This generates a runnable JAR file in the target/ directory.

 
 
 
 
Maven clean completed successfully 
 
Maven install completed successfully

 
 
Runnable JAR file is successfully created on the target directory .
________________________________________
🚀 8. Run the Executable JAR
Test the app by running the executable JAR :
java -jar target/finance-calc-1.0-jar-with-dependencies.jar 10000 7.5 3 1


(Where 10000 7.5 3  2 are input arguments for operations)
________________________________________
🐳 Docker Setup & Dockerization of Java Financial Calculator

🔧 1. Install Docker on Ubuntu
👤 2. Add User to Docker Group
🗂️ 3. Create the dockerProjects Directory

 
Created a dedicated folder for all Docker-related files, keeping things organized.
________________________________________
📦 4. Copy the JAR File into the Directory
Copied the compiled executable JAR (from the Maven project) into the Docker workspace for image creation.
________________________________________
🛠️ 5. Create the Dockerfile
 
 
Defines the Java environment, copies the app, and sets default execution parameters for your calculator.
________________________________________
👀 6. Confirm Dockerfile Content
Use this to double-check the contents of your Dockerfile before building to avoid syntax or path errors.
cat dockerProjects/Dockerfile
 

🏗️ 7. Build the Docker Image
cd dockerProjects
docker build -t devops_project:1.0 .

Builds a Docker image from the Dockerfile. -t tags it for easy identification as devops_calc version 1.0.
________________________________________
🚀 8. Run the Docker Container

docker run devops_calc:1.0

Launches a container from the image and runs the financial calculator app with sample arguments (like Principal, Rate, Operation Type, and Time).
 
Runs financial calculator JAR and prints the result for the provided inputs
🧪 JUnit Testing for Java Financial Calculator
________________________________________
🧩 1. Add JUnit Dependency in pom.xml

Open pom.xml file and ensure the following snippet is added inside the <dependencies> section:
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.12</version>
  <scope>test</scope>
</dependency>

This imports the JUnit 4.12 framework, allowing you to write and run test cases for all calculator methods.
________________________________________
📁 2. Create Test Class CalcTest.java

Inside your Maven project, navigate to:
src/test/java → com.devops.calcProject
Then, create a new Java class named ProjectMainTest.java. This folder is dedicated for unit testing in Maven-based projects.
________________________________________
🧪 3. Write JUnit Test Cases

Paste JUnit test code into ProjectMainTest.java.
 
These test both valid and invalid cases for your calculator methods, ensuring correctness and catching bugs.
________________________________________
🧪 4. Run All Tests via Maven

Right-click on your project → Run As → Maven test
 
This command automatically finds and runs all test files under src/test/java and shows results in the console.

 
________________________________________
🔍 5. Run JUnit Test Class Individually (Optional)
To run just ProjectMainTest.java:
•	Right-click on the class file
•	Click Run As → JUnit Test
This displays a graphical green/red bar interface indicating which tests passed or failed.

 

All Test Cases were successful .








☸️ Deploying Financial Calculator on Kubernetes with Minikube
________________________________________
🔧 1. Install Kubernetes (Minikube)

Minikube helps you create a lightweight Kubernetes cluster locally.
Set up Minikube on your Ubuntu system.

🚀 2. Start Minikube and Connect Docker Daemon

To work with Minikube's internal Docker environment:
minikube start
eval $(minikube docker-env)

This ensures Docker images built will be available to your Kubernetes pods.
 
________________________________________
🛠️ 3. Build the Docker Image Inside Minikube

Navigate to Dockerfile directory and build the image:
docker build -t devops_project:1.0 .

This creates the devops_project:1.0 image directly inside Minikube’s Docker engine.

📦 4.  Enable Minikube Registry & Push Image

If you prefer pushing to Minikube’s internal registry:
minikube addons enable registry
docker tag devops_project:1.0 $(minikube ip):5000/devops_project:1.0
docker push $(minikube ip):5000/devops_project:1.0

⚠️ Skip if image was already built with eval $(minikube docker-env).

 ________________________________________
📝 5. Create Kubernetes Deployment YAML File

Create the deployment file:
sudo nano calculator-deployment.yaml

   

This configures a pod to run the Dockerized calculator app.
📡 6. Apply Deployment and Expose Service

Run the following commands to deploy and expose the app:
kubectl apply -f calculator-deployment.yaml
kubectl expose deployment calculator-deployment --type=NodePort --port=8080

This makes the app available over a NodePort in your Minikube cluster.
________________________________________
📊 7. Launch Dashboard and Monitor Logs

Start the Minikube dashboard to visually monitor pods and logs:
minikube dashboard

 
 

Go to Pods → calculator-deployment → Logs to verify the container output.
 
________________________________________

















📊 Monitoring using Grafana and Graphite
This stage integrates Graphite (for storing system metrics) and Grafana (for beautiful visual dashboards) to monitor your Java-based Financial Calculator application.
________________________________________
🐳 1. Install Docker and Docker Compose ( and Nagios )
 
________________________________________
📁 2. Configure Docker Compose for Grafana and Graphite
 
 
 ________________________________________
🌐 3. Access Grafana Dashboard
🔗 Open Grafana in browser and sign in with credentials . If new both username and password are admin then set new password.
 ________________________________________
➕ 4. Add Graphite as Data Source in Grafana
🧩 Navigate:
Settings → Data Sources → Add Data Source → Choose "Graphite"
🛠️ After proper Configuration Click Save & Test
 ________________________________________
📈 5. Import Dashboard Template
📥 Import community dashboard:
•	Click Dashboard → Import
•	Enter Dashboard ID: 55
•	Select Graphite as data source and import
🖥️ Now see a predefined system metrics dashboard.________________________________________
📡 6. Install and Configure Collectd
📦 Install Collectd:
 
🔌 Enable essential plugins: Important
________________________________________
🔄 7. Restart Collectd
♻️ Restart the service:
sudo systemctl restart collectd
sudo systemctl status collectd
________________________________________
📊 8. Visualize Metrics in Grafana
📊 Metrics like:
•	CPU usage
•	RAM usage
•	Disk utilization
•	Network bandwidth
will now stream live from Collectd → Graphite → Grafana, helping to monitor the application/server health.

 
🛠️ Ansible Automation Setup for devops_Project
________________________________________
🔧 1. Install Ansible

Install Ansible using apt:
sudo apt update
sudo apt install ansible -y
ansible –version

Installs Ansible, a configuration management tool used to automate the entire CI/CD and monitoring pipeline.
________________________________________
📁 2. Create Ansible Project Directory

Navigate to Git project repository and create a folder for Ansible:
Keeps the Ansible configuration separate and organized within the project repository.
________________________________________
🧱 3. Create Ansible Roles

Inside the ansible_project, create the required Ansible roles and supporting files:
 
Each role automates a distinct step in the pipeline (e.g., Java installation, Docker build, Kubernetes deploy, etc.)
________________________________________
📜 4. Create site.yml (Master Playbook)

Create a central playbook file to execute all roles in sequence:
 

This playbook controls the execution order of all roles on your local host.
________________________________________
📂 5. Create inventory.ini File

Create inventory file to define the target machine (here: localhost):
 
Specifies where the Ansible roles should run — in this case, on your own system.
 
________________________________________
📁 6. Add Tasks in Each Role (main.yml)

Navigate into each role’s tasks folder and add commands specific to the pipeline.
 
 
 
 





 
 
Each role automates one major DevOps phase of the project.
🚀 7. Run the Ansible Playbook

Now execute the entire pipeline automation:
bash
Copy code
ansible-playbook -i inventory.ini site.yml

 

This will run all roles sequentially: Java setup → Maven build → Docker build → Test → Deploy → Enable Monitoring.










CI/CD Pipeline Setup for Java Financial Calculator using Jenkins 
________________________________________
🧩 1. Install Jenkins and set up 
🧩 2. Install Required Jenkins Plugins

Go to http://localhost:8080 → Manage Jenkins → Manage Plugins
•	Under "Available" tab, install:
o	Hudson Post Build Task Plugin
o	 PostBuildScript Plugin
These plugins allow Jenkins to run post-build tasks like executing the generated JAR file.________________________________________
📤 3. Push Your Maven Project to Git Server

Move your pom.xml and src/ folder into the Git repository, then:
git add .
git status
git commit -m "Added POM and src"

 
________________________________________
🔐 4. Grant Jenkins Ownership of the Repository

Ensure Jenkins can read/write to the Git repo:
sudo chown -R jenkins:jenkins /home/devops/devops_repository
sudo chmod -R 755 /home/devops/devops_repository

 
________________________________________
📝 5. Add Required Maven Plugin to pom.xml

Edit the pom.xml file to ensure it compiles with Java 17
 
________________________________________
🧪 6. Validate Jenkins Git Plugin

Go to: Manage Jenkins → Script Console and run:
System.properties['hudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT'] 
If result ≠ true, edit: 
sudo nano /etc/default/jenkins 
Add: 
JAVA_ARGS="-Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true"
 
👉 In this project, we will explore both the Freestyle Project and the Pipeline methods in Jenkins to automate the build, test, and deployment processes of our Java-based Financial Calculator.
For freestyle project continue from step 7 . 

🛠️ 7. Create a Freestyle Jenkins Project

From Jenkins homepage:
→ Click New Item
→ Select Freestyle Project
→ Name it project-pipeline
 
This is our CI/CD job configuration.
🔗 8. Connect Git Repository to Jenkins

Inside your new Jenkins project:
→ Go to Source Code Management
→ Select Git
→ Enter local repo path or URL

 
Jenkins will now pull code from the Git repo.
________________________________________
🔨 9. Configure Build Command

→ Click on Add Build Step → Execute Shell

Paste:
mvn clean package -Dmaven.compiler.source=17 -Dmaven.compiler.target=17

This builds the JAR using Maven inside Jenkins workspace.
To automate all the processes________________________________________
🔑 10. Grant Jenkins Sudo Access Without Password

Edit the sudoers file:
sudo visudo

Add this line:
jenkins ALL=(ALL) NOPASSWD: ALL

 
Jenkins can now execute post-build scripts with root access.
________________________________________
📜 11. Post-Build Script to Copy & Run JAR

In Post-build Actions → Add:
•	Add post-build action → Execute Scripts
•	Paste:
sudo cp /var/lib/jenkins/workspace/project-pipeline/target/devopsProject-1.0-jar-with-dependencies.jar /home/devops/devops_repository

java -jar "/home/devops/devops_repository/devopsProject-1.0-jar-with-dependencies.jar" "10000" "7.5" "3" "2"

 

This runs the compiled JAR file after successful build.
________________________________________
🧪 12. Test the Jenkins Pipeline

From Jenkins homepage:
→ Click Build Now on project
 
 
 
View real-time logs from Build History → Console Output.
 
⚙️ Automating All Roles By Running Ansible Playbook In Jenkins
In the project so far, Jenkins automates key tasks like performing a maven clean, building the project, and running the generated JAR file during every build. This ensures continuous integration is in place. However, to streamline the entire DevOps process even further, we are now taking it a step ahead — by triggering the Ansible playbook from Jenkins. This playbook includes all roles such as Maven clean and build, Docker contanerization, JUnit testing, Jenkins setup, Minikube deployment, and monitoring configuration using Grafana and Graphite. 
By doing this, Jenkins not only handles the build but also automates the full environment setup with a single trigger, making the pipeline faster, consistent, and completely hands-free for end-to-end deployment and monitoring.
▶️Run the entire pipeline using the Ansible playbook

👉 Once all roles and their tasks are defined, execute the entire pipeline:
ansible-playbook site.yml -i inventory.ini 

 

📦 This will automatically install, configure, test, deploy, monitor, and integrate everything defined across all roles in a single command.

________________________________________
📋 Note: Checking the Console Output in Jenkins after a build helps in identifying and resolving errors during execution. If a failure occurs while running the Ansible playbook, it's recommended to review and modify the main.yml file under each role's tasks/ directory. Ensuring the tasks, paths, modules, and configurations in these files are correct is key to troubleshooting and fixing build or deployment issues efficiently. Make sure you commit and push the changes . 
________________________________________
 




 
🔁 Now, we transition from using a Freestyle Project to a Jenkins Pipeline for our project. The pipeline approach provides better automation, flexibility, and control, allowing us to define the entire CI/CD workflow in a script (Jenkinsfile). This ensures a more scalable and manageable way to build, test, and deploy our financial calculator application.








🚀Creating Jenkins Pipeline with Ansible Integration
________________________________________
🧱 1. Create a Pipeline Project in Jenkins
🔹 Go to Jenkins Dashboard -> New Item 
🔹 Enter a name, eg . pipeline_for_project.
🔹 Select “Pipeline” as the project type.
🔹 Click OK to create the pipeline project.
🎯 A Pipeline job allows to define the complete build process—build, test, deploy, monitor, and rollback—as code in a Jenkinsfile.
________________________________________
🛠️ 2. Configure the Pipeline Job
After the project is created:
🔹 Scroll down to the Build Triggers section and  Check the box ✅ Poll SCM.
🕒 What is Poll SCM?
This tells Jenkins to poll the Git repository periodically to check for changes (commits, file updates). If changes are found, the pipeline build is triggered automatically.
•	In the Schedule textbox, type:
H/2 * * * *
⏱️ This means: “Check for changes every 2 minutes, randomized per job to avoid overload.”
 H/2 → Distributes the load evenly by using a hash of the job name.
 * → Every minute/hour/day/week/month.
📁 Configure the Pipeline Source:
🔹 In the Pipeline section below:
o	Select “Pipeline script from SCM”.
o	Choose Git under SCM.
o	In the Repository URL, enter the path to your local Git repository (e.g., /home/administrator/devops_repository/).
o	Leave credentials empty (for local access).
o	Set Script Path as: Jenkinsfile (Assuming Jenkinsfile is located at the root of the repo.)
🔹 Click Save.
 
 
🧠 Jenkins will now run the Jenkinsfile from Git repository every 2 minutes (if changes are found) and follow the defined stages.
________________________________________
📝 3. Create the Jenkinsfile
Inside your Git project directory (e.g., devops_repository), create a file named Jenkinsfile and add the following:
 
🧩 Each stage here corresponds to a role or task in the Ansible automation. The playbook (site.yml) is executed with the respective --tags like build, test, etc., and passes required variables (build_path, build_number) to make the process dynamic and traceable.
________________________________________
⚙️ 4. Build the Pipeline
To trigger the pipeline No need to manually click "Build Now" every time.
•	Every 2 minutes, Jenkins checks the Git repository (your local repo path in the config).
•	If it detects any new commit, Jenkins will automatically trigger the build using the latest version of the Jenkinsfile.
So the workflow becomes:
1.	You make changes in code or configuration.
2.	You commit and push the changes to  Git repository.
3.	Jenkins polls the repo, sees the new commit, and triggers the entire pipeline automatically.


 
 
 
Pipeline working successfully🛠️

________________________________________







🔄 Git Polling and Build Trigger Demonstration
________________________________________
Jenkins is configured to poll the Git repository every 2 minutes for changes.
If any new commits are detected, it automatically triggers the pipeline build process.
This helps ensure that the latest updates are always tested and deployed without manual intervention.
 

If there are no changes, the pipeline remains idle, avoiding unnecessary builds.













Updated Playbook and Roles for Smooth Pipeline Execution and Achieving Proper Rollback
    
   
   
🧯 Rollback Implementation – Auto-Recovery
________________________________________
🔄 1. Install Version Number Plugin

Go to Manage Jenkins → Plugin Manager → Available
→ Install Version Number Plugin
Helps manage versions like 0.2.$BUILD_NUMBER for rollback.
________________________________________
💣 2. Intentionally Break a Test Case

→ In  Git repo, modify ProjectMainTest.java to fail a test.

Correct Test Case (with testValidCompoundInterest result as 1025)
 
Intentionally making the Test case to Fail by changing the value as 125
 
________________________________________
🔧 3. Update Build Commands or Jenkinsfile for Versioning 
________________________________________
📈 4. Trigger Jenkins Build and Observe

→ Commit the change to trigger a build failure:
→ Watch for:
❌ Build Failure
✅ Rollback Script Execution
✅ Jenkins has now auto-recovered from the failed build using rollback.




 
Failure of Test case



Rolling back to Previous Build
📌 Migration from Local Git Repository to GitHub
In the initial stages of the project setup, we created a local Git server and bare repository (git@localhost:project_repository.git) to manage version control and integrate with Jenkins. This worked effectively in a closed environment, allowing us to test Git-based automation like SCM polling and CI/CD triggers.
However, to make the project more scalable, collaborative, and accessible remotely, migrate the Git repository to GitHub.
________________________________________
🔄 What We Changed
1.	Jenkins Configuration:
o	Replace or use Github repository URL in place of Local repository path . Update under 
	Freestyle Project: Git → Repository URL
	Pipeline Project: Script from SCM → Repository URL
Jenkins is now connected to the GitHub repository instead of the local Git server.
2.	SCM Trigger:
o	No change needed in trigger logic (Poll SCM still works), but it now checks GitHub instead of local Git.
o	Optionally, GitHub Webhooks can be configured for real-time triggering instead of polling.
3.	Push Workflow:
o	Code is now committed and pushed to GitHub . 
________________________________________
⚙️ How Jenkins Works After Migration
•	It continues to:
o	Poll GitHub every 2 minutes for changes (based on H/2 * * * * schedule).
o	Trigger builds automatically if new commits are detected.
o	Run Ansible playbooks for build, test, dockerization, deployment, monitoring, and rollback.
•	No disruption to your existing Ansible roles or pipeline logic — only the Git source URL changed.


💡 Why We Switched to GitHub
Reason	Benefit
🌍 Remote Access	Access code from anywhere, not just the local machine
🤝 Collaboration	Easily share and collaborate with team members
🔐 Better Security	GitHub offers role-based access, SSH key integration
🧩 CI/CD Integration	Jenkins works seamlessly with GitHub for SCM polling or webhook triggers
☁️ Cloud Backup	Prevents data loss by storing your code in the cloud
________________________________________
✅ Conclusion
This project presents a comprehensive end-to-end DevOps pipeline designed to automate the entire lifecycle of a Java-based CLI application. It starts with setting up a Maven project for building and managing dependencies, followed by the integration of JUnit for automated testing to ensure code quality. The application is then containerized using Docker, making it portable and consistent across environments. This Dockerized application is deployed to a Kubernetes (Minikube) cluster, offering scalability and efficient resource management.
To enable seamless automation, Ansible is used for orchestrating various stages like build, test, dockerization, deployment, monitoring, and rollback. A centralized site.yml playbook and well-structured role-based automation scripts ensure that each task is repeatable, maintainable, and modular. These Ansible playbooks are executed through Jenkins, which acts as the heart of the CI/CD pipeline. The Jenkins pipeline is configured with SCM polling, so every new code push triggers the entire process automatically. This minimizes manual intervention and ensures rapid and reliable delivery of updates.
Furthermore, robust monitoring using Collectd, Graphite, and Grafana is integrated to visualize system metrics in real time. This allows for proactive identification of issues in deployed environments. In case of a failure during build or deployment, automatic rollback mechanisms ensure stability by reverting to the last successful version of the application. Altogether, this project demonstrates not only the technical integration of industry-standard DevOps tools but also emphasizes the importance of automation, resilience, and observability in modern software delivery workflows.
🔁 DevOps as a practice bridges the gap between development and operations teams by promoting collaboration, continuous integration, and delivery. It helps organizations accelerate product releases, improve deployment frequency, and ensure system reliability. By embracing DevOps principles, this project reflects how automation and toolchain integration can drastically reduce deployment risks, enhance feedback loops, and maintain high-quality software standards at scale
________________________________________
🔗 GITHUB LINK
https://github.com/Dhaya09/DevOpsProject.git
