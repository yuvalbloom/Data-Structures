# Doubly Linked List Data Structure

class Node():
	
	def __init__(self, val):
		self.value = val
		self.next = None
		self.prev = None
		
	def __repr__(self):
		return str(self.value) + "(" + str(id(self))+ ")"


class DLList():

	def __init__(self, seq=None):
		self.head = None
		self.tail = None
		self.len = 0
		if seq != None:
			for item in seq:
				self.insert(item)
 
	def __len__(self):
		return self.len
	 

	def __repr__(self):
		out = ""
		p = self.head
		while  p != None :
			out += str(p) + ", " # str(p) envokes __repr__ of class Node
			p = p.next
		return "[" + out[:-2] + "]"
		
			
	def insert(self, val, first = False):
		'''insert new value to linked list'''
		p = Node(val)
		if self.head != None and not first:
			if self.tail != None:
				self.tail.next = p
				p.prev = self.tail
			if self.tail == None:
				self.head.next = p
				p.prev = self.head
			self.tail = p
		else:
			if self.head != None and first:
				self.head.prev = p
				p.next = self.head
			self.head = p
			if self.tail == None and first:
				if self.head.next == None:
					self.tail = self.head
				else:
					self.tail = self.head.next
		self.len += 1
			 
	def reverse(self):
		'''reverse double linked list'''
		curr = self.head
		while curr != None:
			tmp = curr.next
			curr.next = curr.prev
			curr.prev = tmp
			curr = tmp
		self.head, self.tail = self.tail, self.head
	
	def rotate(self, k):
		'''rotate double linked list'''
		m = self.len - k
		self.head.prev = self.tail
		self.tail.next = self.head
		if m < k:
			for _ in range(m):
				self.head = self.head.next
				self.tail = self.tail.next
		elif m >= k:
			for _ in range(k):
				self.head = self.head.prev
				self.tail = self.tail.prev
		self.head.prev = None
		self.tail.next = None

	def delete_node(self, node):
		'''deletes given node from doubled linked list'''
		if node == self.head:
			if node != self.tail:
				self.head = self.head.next
				self.head.prev = None
			self.head = None
			self.tail = None
		if node == self.tail:
			self.tail = self.tail.prev
			self.tail.next = None
		else:
			node.prev.next = node.next
			node.next.prev = node.prev
		self.len -= 1
        