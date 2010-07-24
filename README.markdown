# Dependencies
* Maven 2.X
* Eclipse <= 3.5 with hibernate-tools plugin
* PostgreSQL

# HowTo
## Get the code
* Go to your workspace dir

* Clone the project in a local git repository
        git clone git://github.com/cholin/WM.git
        cd WM

* Install the project in your local mvn repository and make a eclipse project
        mvn install 
        mvn eclipse:eclipse

* Start Eclipse
        New -> Java Project
            [x] Create Project from existing Source

## Configure hibernate
* Switch to the hibernate perspective
* Click the hibernate config window
* Right-click and click "Add Configuration"
        Main:
            Type: [x] Annotations
            Project: Your Project
            Configuration file: /WM/src/main/resources/hibernate.cfg.xml
    
        Options:
            Database dialect: PostgreSQL
