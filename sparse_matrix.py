# create a sparse matrix (N x M) class with few functions

class SparseMatrix:
	"""
	Represents a rectangular matrix with n rows and m columns.
	"""

	def __init__(self, n, m):
		"""
		Create an n-by-m matrix of val's.
		Inner representation: list of lists (rows)
		"""
		assert n > 0 and m > 0
		self.rows={}
		self.size = (n,m)
	
	def __repr__(self):
		s = ""
		for i in range(self.dim()[0]):
			l = []
			if i not in self.rows:
				l = [0 for j in range(self.dim()[1])]
			else:
				for j in range(self.dim()[1]):
					if j in self.rows[i]:
						l.append(self.rows[i][j])
					else:
						l.append(0)
		
			s += str(l) + "\n"
			
		return s
	
	def dim(self):
		return self.size
		
	def __eq__(self, other):
		assert isinstance(other, SparseMatrix)
		return self.rows == other.rows and self.size == other.size
	
	def __getitem__(self, ij): #ij is a tuple (i,j). Allows m[i,j] instead m[i][j]
		if ij[0] > self.size[0] or ij[1] > self.size[1]:
			return 'index out of bounds'
		if ij[0] not in self.rows or ij[1] not in self.rows[ij[0]]:
			return 0
		return self.rows[ij[0]][ij[1]]


	def __setitem__(self, ij, val): #ij is a tuple (i,j). Allows m[i,j] instead m[i][j]
		if ij[0] > self.size[0] or ij[1] > self.size[1]:
			return 'index out of bounds'
		elif ij[0] <= self.size[0] and ij[1] <= self.size[1]:
			if ij[0] in self.rows.keys():
				self.rows[ij[0]][ij[1]] = val
				return
			elif ij[0] not in self.rows.keys():
				self.rows[ij[0]] = {}
				self.rows[ij[0]][ij[1]] = val
				return

					
	def __add__(self, other):
		assert self.size == other.size
		added_mat = SparseMatrix(self.size[0],self.size[1])
		for i in self.rows:
			added_mat.rows[i] = {}
			for j in self.rows[i]:
				added_mat[i,j] = self[i,j]
		for i in other.rows:
			if i in added_mat.rows:
				for j in other.rows[i]:
					added_mat[i,j] = self[i,j] + other[i,j]
			if i not in added_mat.rows:
				added_mat.rows[i] = {}
				for j in other.rows[i]:
					added_mat[i,j] = other[i,j]
		return added_mat



	def __neg__(self):
		neg_mat = SparseMatrix(self.size[0],self.size[1])
		for i in self.rows:
			for j in self.rows[i]:
				neg_mat[i,j] = self[i,j]*(-1)
		return neg_mat


	def __sub__(self, other):
		assert self.size == other.size
		sub_mat = self + (- other)
		sub_mat_2 = self + (-other)
		temp = True
		for i in sub_mat_2.rows:
			for j in sub_mat_2.rows[i]:
				if sub_mat.rows[i][j] != 0:
					temp = False
				else:
					del(sub_mat.rows[i][j])
			if temp:
				del(sub_mat.rows[i])
		return sub_mat
	

	def __mul__(self,other):
		assert isinstance(other, SparseMatrix)
		assert self.dim()[1] == other.dim()[0]
		
		n,m = self.dim()[0], other.dim()[1]
		new = SparseMatrix(n,m)

		#create columns dict for other
		other_cols = {}
		for i in other.rows:
			for j in other.rows[i]:
				if j not in other_cols:
					other_cols[j] = {}
				other_cols[j][i] = other.rows[i][j]
		
		for i in self.rows:
			for j in other_cols:
				s_ij = 0
				rows = self.rows[i]
				for k in self.rows[i]:
					if k in other_cols[j]:
						s_ij += self.rows[i][k] * other_cols[j][k]
				if s_ij != 0:
					if i not in new.rows:
						new.rows[i] = {}
					new.rows[i][j] = s_ij
		return new
        