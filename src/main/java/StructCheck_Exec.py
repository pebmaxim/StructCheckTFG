import sys, os
"""
The purpose of this script is to make the use of StructCheck simpler by automatically compiling the .java file
containing all the checks and then executing the checkstyle. Initial version of this script supports WindowsOS and Linux.
In case of using a different checkstyle jar version, replace in this script.

IN ORDER TO USE IT:
The python file needs to be executed in the terminal with the command "python StructCheck_Exec.py"
This file should be located on the level of the java package folders
"""
user_os = sys.platform
match user_os:
    case "win32":
        os.system('javac -cp "checkstyle-10.21.2-all.jar;StructCheck.jar" ./structchecks/StructChecks.java')
        os.system('java -cp StructCheck.jar;checkstyle-10.21.2-all.jar;./ com.puppycrawl.tools.checkstyle.Main -c config.xml modelo')
    case "linux":
        os.system('javac -cp "checkstyle-10.21.2-all.jar:StructCheck.jar" ./structchecks/StructChecks.java')
        os.system('java -cp StructCheck.jar:checkstyle-10.21.2-all.jar:./ com.puppycrawl.tools.checkstyle.Main -c config.xml modelo')
    case default:
        print("ERROR: The laptop is running an operating system unaccounted for in this script")