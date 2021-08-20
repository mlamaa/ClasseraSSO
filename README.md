# **Classera SSO Login Demo**
**Android Mobile Application java based code**

 

**Steps**
1. Step one: open web view as ActivityForResult
2. Open the web view with classera URL
3. Monitor the redirection
4. Parse URL to get data from splitQuery
5. Append data to intent and set it as a result
6. Set intent result code as OK
7. Set intent result code as canceled if any exception happened
8. Finish the webview
9. If requestCode is correct and resultCode is ok, we should get the data from the intent "data"
10. If requestCode is incorrect and resultCode is not ok, display error message


