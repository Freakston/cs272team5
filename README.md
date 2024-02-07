# CS272 Project Description
## AFL++: Combining Incremental Steps of Fuzzing Research
### Andrew Peng, Khalid Mihlar, Suraj K Suresh, Setareh Khoshnoo, Brian Chen

**Introduction**
The base idea of our project would be the integration of fuzzers into the CI/CD pipeline. We will be using a tool called AFL++ which will aid extending the fuzzing process at many stages. Our goal would be applying this tool to a CI/CD pipeline to showcase a proof-of-concept, being an application project beyond the scope that the paper covers. This would require us to build/use an existing code base, fuzz it, and then find reports that were introduced based on triggers set by the users. This could be something like commits, merges, or any sort of code addition. 

**Motivation for the Project**
The integration of a fuzzer into a projects CI/CD pipeline offers several key benefits:
+ Early Detection of Vulnerabilities: Security issues are identified early in the development process, reducing the cost and effort required for remediation.
+ Automated testing and Continuous testing: Once the fuzzer is deployed as part of the pipeline it runs continuously without any manual intervention.
+ Highly customizable based on developers needs: Since it's part of the CI/CD the developer gets to decide what triggers on the codebase triggers spawning new fuzzing instances or new campaigns.

**Pipeline design**
This is a rough outline of what the pipeline will look like. This might change depending on the challenges we might encounter once we start implementing it.
+ The first step would involve ensuring compatibility with various CI/CD frameworks. The pipeline is planned to seamlessly integrate with the popular CI/CD tools, such as GitHub Actions, GitLab, Jenkins, etc. This allows development teams to choose a framework that aligns with their existing workflow.
+ We plan on using docker, as it will allow us to spawn multiple copies of the same software in a controlled environment.
+ These containers will be orchestrated by our pipeline and each of them will be running an instance of AFL++ inside it.
+ The crashes will be collected by our pipeline and parsed into a friendlier format. All the crashes will be displayed on a simple webpage.

**Potential Challenges**
+ Harnesses are a very important part of an effective fuzzer, but their makeup depends highly on the codebase that they are applied to. In order to have the best fuzzing campaign, tailored harnesses are necessary. At the moment, the harness is written manually by humans who either reverse engineer the program or read the source code.
For the starting phase of the project we will be using popular open source projects that have been fuzzed before and create a POC of the pipeline.



