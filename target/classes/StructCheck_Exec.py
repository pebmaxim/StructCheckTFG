import sys, os, glob
"""
The purpose of this script is to make the use of StructCheck simpler by automatically compiling the .java file
containing all the checks and then executing the checkstyle. Initial version of this script supports WindowsOS and Linux.
In case of using a different checkstyle jar version, replace in this script.

IN ORDER TO USE IT:
The python file needs to be executed in the terminal with the command "python StructCheck_Exec.py" or similar.
This file should be located on the level of the java package folders
"""
user_os = sys.platform

import glob

files = glob.glob('*-all.jar')
try:
    cs_jar = files[0]
except IndexError:
    print("No se ha encontrado el .jar de checkstyle. Parando ejecucion.")
    exit()

try:
    StructCheck_jar = glob.glob('StructCheck.jar')[0]
except IndexError:
    print("No se ha encontrado el .jar de StructCheck. Parando ejecucion.")
    exit()


match user_os:
    case "win32":
        os.system('javac -cp "'+ cs_jar + ';'+ StructCheck_jar + '" ./tests/StructChecks.java')
        os.system('java -cp '+ StructCheck_jar + ';'+ cs_jar + ';./ com.puppycrawl.tools.checkstyle.Main -c config.xml modelo')
    case "linux":
        os.system('javac -cp "'+ cs_jar + ':'+ StructCheck_jar + '" ./tests/StructChecks.java')
        os.system('java -cp '+ StructCheck_jar + ':'+ cs_jar + ':./ com.puppycrawl.tools.checkstyle.Main -c config.xml modelo')
    case default:
        print("ERROR: The laptop is running an operating system unaccounted for in this script")
