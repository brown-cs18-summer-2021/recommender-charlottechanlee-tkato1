Names:
Charlotte Lee
Toshiki Kato

Bugs:
There are no known bugs for this program.

How our code works:

ITreeNode is an interface with classes
- Node: has fields attribute (String), mostCommon (Object), and edgeList (LinkedList<Edges>)
mostCommon stores the mostCommon value of the target attribute, which is the attribute
that we are trying to predict (ex: whether we like to eat a vegetable).
- Leaf: has field attributeValue (object)

Edge is a class that has the field edgeVal (Object) and nextNode (ITreeNode)

As you can see, these classes mentioned above create the skeleton of a decision tree.
A node is represented by the attribute (ex: color), and it has a list of edges that correspond
to that attribute (ex: blue, yellow, red). Because these are of class Edge, these all
refer to another node in the nextNode field, and so they cycle between nodes and edges
continue until a leaf is reached.

- Node and Leaf have a method called lookupDecision, which takes in datum (IAttributeDatum)
and returns the decision of the decision tree. lookupDecision on a node will loop
through all of the edges for that node and check if there is a match. For example,
if the trainingData was trained on only blue and yellow vegetables, the decision tree
will not have any other edges created. Thus, there will not be a match and the method
will just return the most common value for the target attribute.

When called on a Leaf, this means that datum has matched something for every edge on
the tree, and it will return the attribute value of the Leaf.

Method printNode prints the Node or Leaf. It takes in leadSpace (String) which acts as an indention for
every edge of a node. It first prints the attribute the node is based on. Then the edge value are printed
in a new line with the leadSpace indention. If an edge leads to another node, the attribute that the node is based
on is printed. Then, the edge value for that node is indented for double the length of leadSpace. If an edge
leads to a leaf, then the value of the attribute the tree/node is predicting/recommending is printed.

IAttributeDataset is an interface with class
- DataTable:
has fields attributes (List<String>), datumList (List<T>). There are various, relatively
simple functions that allow us to return the list of attributes (essentially, the list of all the
categories like color, calories, and whether a vegetable is high carb or low fiber). We can also get
the list of all data objects (ex: returns a list of vegetables). We can find the size (ex: number of
vegetables). We can check if all of the vegetables have the same value for a certain attribute,
as well as check what the value of that attribute is. (Do all the vegetables have the same color?
If so, what color?).

This class also has some more complex functions. mostCommonValue takes ofAttribute (String).
We can return the most common value of ofAttribute which is useful because we need to store the
most common value in case there is no edge match (as mentioned above).We made two empty lists
 1) attributeValues and 2) attributeValueCounts.
The code will loop throughout the table, looking at every object. If the list of attributeValues
already contains the currentAttribute value, then that means we've already seen this attributeValue
before. So we will go to attributeValueCounts list and update that value. If we have not seen this
attribute before, then we will add a new attributeValue to the attributeValueList - at the end of
the list. Then we will create a corresponding count in the attributeValueCounts list starting with 0.

Partition creates a list of subsets if you give it the input ofAttribute (String) that you want
it to base its partitions off of. For example, if you give it "color", then it will give you a list
of DataTables, and the first datatable only has vegetables that are green, and the second, only has
vegetables that are orange, and so on until all unique values of attribute color has its own subset.
Partition creates an empty LinkedList<IAttributeDataset<T>> which is going to be the list that the method
will output. Then a for loop is made for each datum in the current dataset. A marker of whether the datum
the loop is on has been added to a subset is made. Then, the size of the output list for the next for loop.
The for loop nested in the initial one compares the ofAttribute value of datum the loop is on to each
of the shared values of the ofAttribute value for each subset in outputList. If a match is made, the current
datum is added to the subset list and the marker of whether the datum has been added to a subset is made true.
After the nested for loop, an if statement checks to see if the current datum has been added to a subset through
getting the value of the marker. If it has not been added. A new subset is made and added to output list and
that current datum is added to the new subset. Finally at the end, when the first for loop is also completed,
partition will return output list which is again a list of the subsets which are partitioned based on the
onAttribute attribute.


IAttributeDatum is an interface with class
-Vegetable: has fields for each attribute, and the field type depends on the attribute.
For example, the field color is a string, while the field highCarb is a boolean. There
is a function that takes in the string version of the attribute and returns the value, which
essentially allows us to access the fields of the datum.

ITreeGenerator is an interface with class
-TreeGenerator: has fields trainingData (IAttributeDataset<T>) and root(ITreeNode)
The "root" is an entire decision tree represented by the root node.

There is a helper, buildClassifierHelper, which takes in the targetAttribute (String)
and a subset (IAttributeDataset<T>). It will remove the targetAttribute from the list
of attributes the first time it is ran because we want to build the tree based off of other attributes.
There is no point making a prediction based off of the quality/attribute we want to predict.
If everything within the datatable has the same value for the targetAttribute, then
we can simply return that value of the targetAttribute in the form of a leaf (prediction is easy in this case).
If the attribute list has no more attributes, then we return a Leaf with the most common value of the
targetAttribute within that input subset. Otherwise, we will construct a node for each randomly chosen attribute
within the unused attribute list, storing the value of the attribute within the node's fields, as well as
looping through the partitioned list so that we have a list of edges to include in the node's edgeList field as well.

buildClassifier assigns the this.root field to the ouput of buildClassifierHelper called called on
targetAttribute and the trainingData. Essentially, it will create a root representing the whole tree,
and the tree is created by randomly picking unused nodes, splitting the trainingData using partition, and
continuing recursively.

method lookupRecommendation takes in a datum (IAttributeDatum) and calls on lookupDecision from the ITreeNode
interface to the root node of the tree. It will return the Object that lookupDecision returns which represents
the estimated or recommended value of the attribute the tree is based on.


method printTree calls on printNode from the ITreeNode interface to the root node of the tree. It will print
the tree with "   " as the input leadSpace for printNode.

