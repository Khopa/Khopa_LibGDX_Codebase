import sys, os
import Image

# This script utility convert HD assets into MD and SD assets
# Python 2.7 is required to run it, and PIL (Python Image Library) too


def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        return False

"""
	if(len(sys.argv) != 3 or not is_number(sys.argv[2])):
		print("Usage: python scalePack.py filename.pack scaleFactor")
		sys.exit()
	filename = sys.argv[1]
	filenameWithoutExtension = filename.split(".")[0]
	imageFilename = filenameWithoutExtension + ".png";
	scaleFactor = float(sys.argv[2])

	imageFiles = []

	#Resize the pack file
	newLines = []
	with open(filename, 'r') as packFile:
		for i,line in enumerate(packFile.readlines()):
			if ".png" in line:
				imageFiles.append(line)
			words = line.split(" ");
			newLines.append([])
			for j,w in enumerate(words):
				if(len(w) > 1 and is_number(w[:-1])):
					removed = w[-1]
					newLines[i].append(str(int(float(w[:-1])*scaleFactor))+removed);
				else:
					newLines[i].append(w)
			newLines[i] = " ".join(newLines[i])

	with open(filenameWithoutExtension + "_" + str(scaleFactor) + ".pack", "w") as output:
		sys.stdout = output
		for l in newLines:
			print(l),

	#Resize the images
	for imageFile in imageFiles:
		imageFile = imageFile[:-1]
		im = Image.open(imageFile)
		size = int(im.size[0]*scaleFactor), int(im.size[1]*scaleFactor)
		im2 = im.resize(size, Image.NEAREST)
		im2.save(imageFile.split(".")[0] + "_" + str(scaleFactor) + ".png", "PNG")
"""


class Resource:

	__packFile = ""
	__imagesFile = []
	__resourceName = ""
	__path = ""

	def __init__(self, path, resourceName):
		self.__path = path
		self.__resourceName = resourceName.split(".")[0]
		self.__imageFiles = []

		files = os.listdir(self.__path)
		for f in files:
			if(".png" in f and f.startswith(self.__resourceName)):
				self.__imageFiles.append(f)

	def __str__(self):
		string  = "-----------------\n"
		string += "Path : " + self.__path + "\n"
		string += "Pack : " + self.__resourceName + "\n"
		for img in self.__imageFiles:
			string += "\tIMG : " + img + "\n"
		string += "-----------------\n"
		return string

	def scaleToDir(self, baseDir, factor):
		pathCopy = self.__path
		newDir = baseDir + os.sep + os.sep.join(pathCopy.split(os.sep)[len(baseDir.split(os.sep)):])
		print(newDir)
		if(not os.path.exists(newDir)):
			os.makedirs(newDir)

		# Read the pack file and create new lines
		newLines = []
		filename = self.__path + os.sep + self.__resourceName + ".pack"
		print("Reading filename")
		with open(filename, 'r') as packFile:
			for i,line in enumerate(packFile.readlines()):
				words = line.split(" ");
				newLines.append([])
				for j,w in enumerate(words):
					if(len(w) > 1 and is_number(w[:-1])):
						removed = w[-1]
						newLines[i].append(str(int(float(w[:-1])*factor))+removed);
					else:
						newLines[i].append(w)
				newLines[i] = " ".join(newLines[i])

		# Write the new pack file
		print("Writing " + newDir +  os.sep + self.__resourceName + ".pack")
		with open(newDir +  os.sep +  self.__resourceName + ".pack", "w") as output:
			for l in newLines:
				output.write(l)

		#Scale the images
		for imageFile in self.__imageFiles:
			print("Resizing " + imageFile)
			location = self.__path + os.sep + imageFile
			newLocation = newDir + os.sep + imageFile
			im = Image.open(location)
			size = int(im.size[0]*factor), int(im.size[1]*factor)
			im2 = im.resize(size, Image.NEAREST)
			im2.save(newLocation, "PNG")


global _resources

def exploreInputDirectory(root):
	global _resources

	subdirs = []
	for resource in os.listdir(root):
		if(os.path.isdir(root+ os.sep + resource)):
			subdirs.append(root + os.sep + resource)
		else:
			if(".pack" in resource):
				_resources.append(Resource(root, resource.split(os.sep)[-1]))

	for subdir in subdirs:
		exploreInputDirectory(subdir)

if __name__ == "__main__":
	_resources = []
	exploreInputDirectory("gfx" + os.sep + "hd")
	print(len(_resources))
	for r in _resources:
		print(str(r))
		print(r.scaleToDir("gfx"+os.sep+"md", 0.5))
		print(r.scaleToDir("gfx"+os.sep+"ld", 0.25))
