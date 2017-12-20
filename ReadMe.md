•	It should contain common code for both Android & iOS wherever it is possible. i.e. only the specific inevitable parts of framework should allow separate code. Add ToDo comments or dummy files wherever iOS related code will come into picture (assume iOS app also exists with similar functionality)
>>This framework will support both iOS and android apps it should be configured platform name if android or iOS in properties file

•	Identify the problematic areas of this app and suggest better guidelines to developer to ensure best fit to automation
>>Developer need to declared every element as attributes and check whether auto correction is removed or not

•	Maintenance of the code should be high i.e. report generation and tying it to Jenkins dashboard should be configurable in future with minimal changes
>>Already added the report thing it will be easily integrated with the jenkins 

•	Don’t miss to add REST API calls related chunk in this framework. BTW, the app fetches some content on Dashboard from Firebase cloud via Rest API
>>Added the Rest API calls and verified 


