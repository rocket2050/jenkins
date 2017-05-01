def baseUrl = "https://api.bitbucket.org"

 String a = "String baseUrl = \"baseUrl\""; 
      println(a.replaceAll("= \"baseUrl\"","= \"$baseUrl\""));
