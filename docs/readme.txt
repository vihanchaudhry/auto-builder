Vihan Chaudhry
CIS 35B
Winter 2016
Lab 3
02/18/16

IDE: IntelliJ IDEA
- Project is provided

Design
- Automobile now has an ArrayList of choices
- Each OptionSet has a choice data member
- ProxyAutomobile contains a static LinkedHashMap of all the models of automobile
- Each method uses Iterators to iterate the hash table
- Map.Entry objects are parsed from the iterator, and their values are casted as Automobile to retrieve object functionality
- Driver instantiates a BuildAuto object 
- The constructor in BuildAuto calls the addAuto method to add an Automobile object to it from a file
- The key of the hash table is set to be the name of the automobile
