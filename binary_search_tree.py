# Binary Search Tree data structure


# This code is a representation of the tree, taken from CS classes
def printree(t, bykey = True):
		"""Print a textual representation of t
		bykey=True: show keys instead of values"""
		#for row in trepr(t, bykey):
		#        print(row)
		return trepr(t, bykey)

def trepr(t, bykey = False):
		"""Return a list of textual representations of the levels in t
		bykey=True: show keys instead of values"""
		if t==None:
				return ["#"]

		thistr = str(t.key) if bykey else str(t.val)

		return conc(trepr(t.left,bykey), thistr, trepr(t.right,bykey))

def conc(left,root,right):
		"""Return a concatenation of textual represantations of
		a root node, its left node, and its right node
		root is a string, and left and right are lists of strings"""
		
		lwid = len(left[-1])
		rwid = len(right[-1])
		rootwid = len(root)
		
		result = [(lwid+1)*" " + root + (rwid+1)*" "]
		
		ls = leftspace(left[0])
		rs = rightspace(right[0])
		result.append(ls*" " + (lwid-ls)*"_" + "/" + rootwid*" " + "|" + rs*"_" + (rwid-rs)*" ")
		
		for i in range(max(len(left),len(right))):
				row = ""
				if i<len(left):
						row += left[i]
				else:
						row += lwid*" "

				row += (rootwid+2)*" "
				
				if i<len(right):
						row += right[i]
				else:
						row += rwid*" "
						
				result.append(row)
				
		return result

def leftspace(row):
		"""helper for conc"""
		#row is the first row of a left node
		#returns the index of where the second whitespace starts
		i = len(row)-1
		while row[i]==" ":
				i-=1
		return i+1

def rightspace(row):
		"""helper for conc"""
		#row is the first row of a right node
		#returns the index of where the first whitespace ends
		i = 0
		while row[i]==" ":
				i+=1
		return i

######################################################################################################################
# Tree Node and Binary Tree Classes

class Tree_node():
	def __init__(self, key, val):
		self.key = key
		self.val = val
		self.left = None
		self.right = None

	def __repr__(self):
		return "(" + str(self.key) + ":" + str(self.val) + ")"
	
	
	
class Binary_search_tree():

	def __init__(self):
		self.root = None


	def __repr__(self): #no need to understand the implementation of this one
		out = ""
		for row in printree(self.root): #need printree.py file
			out = out + row + "\n"
		return out

	
	def lookup(self, key):
		''' return node with key, uses recursion '''

		def lookup_rec(node, key):
			if node == None:
				return None
			elif key == node.key:
				return node
			elif key < node.key:
				return lookup_rec(node.left, key)
			else:
				return lookup_rec(node.right, key)

		return lookup_rec(self.root, key)


	def insert(self, key, val):
		''' insert node with key,val into tree, uses recursion '''

		def insert_rec(node, key, val):
			if key == node.key:
				node.val = val     # update the val for this key
			elif key < node.key:
				if node.left == None:
					node.left = Tree_node(key, val)
				else:
					insert_rec(node.left, key, val)
			else: #key > node.key:
				if node.right == None:
					node.right = Tree_node(key, val)
				else:
					insert_rec(node.right, key, val)
			return
		
		if self.root == None: #empty tree
			self.root = Tree_node(key, val)
		else:
			insert_rec(self.root, key, val)


	def minimum(self):
		''' return node with minimal key '''
		if self.root == None:
			return None
		node = self.root
		left = node.left
		while left != None:
			node = left
			left = node.left
		return node


	def depth(self):
		''' return depth of tree, uses recursion'''
		def depth_rec(node):
			if node == None:
				return -1
			else:
				return 1 + max(depth_rec(node.left), depth_rec(node.right))

		return depth_rec(self.root)


	def size(self):
		''' return number of nodes in tree, uses recursion '''
		def size_rec(node):
			if node == None:
				return 0
			else:
				return 1 + size_rec(node.left) + size_rec(node.right)

		return size_rec(self.root)

	def max_sum(self):
		'''return max sum of values of nodes in tree, uses recursion'''
		def max_rec(node):
			if node == None:
				return 0
			else:
				return node.val + max(max_rec(node.left), max_rec(node.right))
		return max_rec(self.root)


	def is_balanced(self):
		'''returns True if a given binary tree is balanced and False if not'''
		def is_balanced_rec(node):
			if node == None:
				return 1
			else:
				left_height = is_balanced_rec(node.left)
				right_height = is_balanced_rec(node.right)
				if left_height == -1:
					return -1
				if right_height == -1:
					return -1
				if abs(left_height - right_height) > 1:
					return -1
				return max(left_height, right_height) + 1
		return is_balanced_rec(self.root) > -1


	def diam(self):
		'''calculates the diameter of a given binary tree'''
		def len_diam(node):
			'''function that calculates the depth of the tree'''
			if node == None:
				return 0
			else:
				left_len = len_diam(node.left)
				right_len = len_diam(node.right)
				max_len = max(left_len,right_len)
				return max_len + 1
		def diam_rec(node):
			if node == None:
				return 0
			elif node.left == None and node.right == None:
				return 1
			return max(diam_rec(node.left),diam_rec(node.right), len_diam(node.left) + len_diam(node.right) + 1)
		return diam_rec(self.root)