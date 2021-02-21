# Dictionary Data Structure

class Dict:
	def __init__(self, m, hash_func=hash):
		""" initial hash table, m empty entries """
		self.table = [ [] for i in range(m)]
		self.hash_mod = lambda x: hash_func(x) % m

	def __repr__(self):
		L = [self.table[i] for i in range(len(self.table))]
		return "".join([str(i) + " " + str(L[i]) + "\n" for i in range(len(self.table))])
			  
	def insert(self, key, value):
		""" insert key,value into table
			Allow repetitions of keys """
		i = self.hash_mod(key) #hash on key only
		item = [key, value]    #pack into one item
		self.table[i].append(item) 

	def find(self, key):
		""" returns ALL values of key as a list, empty list if none """
		i = self.hash_mod(key)
		lst = [j[1] for j in self.table[i] if j[0]==key]
		return lst
