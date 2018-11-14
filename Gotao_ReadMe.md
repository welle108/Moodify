Sprint #1 - 11/13/2018

Mostly focused on learning how to get the Spotify API up and running in Java. The hardest part of this sprint was figuring out how to set 
the request properties of the URL connection, since the Spotify documentation is a little esoteric in writing. After a lot of research and 
testing, I was finally able to pull an access token. The next hardest task was to build the GET request in order to pull data from the API.
Thanks to the Spotify console, this was easier to execute, since it was able to modularly show me how to build the request. Then it was all
a matter of parsing and reading through the JSON object, which was thankfully a more forgiving task as Google's GSON library was very easy 
to use.
