package no.nils.wsdl2java;

/**
 * Takes one or more ObjectFactory java files and merge them, sorting the lines in the process.
 * This code relies *entirely* only blank lines as separaters between methods. It is not very clever.
 */
public class ObjectFactoryMerger {
	private static final String NEWLINE = System.getProperty("line.separator");
	private String packageDef;
	private String constructorDef;
	private List<String> classDef = new ArrayList<>();
	private Set<String> imports = new HashSet<>();
	private Set<String> constants = new HashSet<>();
	private Set<CreateMethod> createMethods = new HashSet<>();
	private String encoding;
	
	private ObjectFactoryMerger(String encoding) {
		this.encoding = encoding;
	}
	
	public static void merge(File src, File into, String encoding) throws IOException {
		new ObjectFactoryMerger(encoding).mergeInto(src, into);
	}
	
	private void mergeInto(File src, File dest) throws IOException {
		load(src);
		load(dest);
		
		String merged = reassemble();
		dest.withWriter(encoding) { it.write(merged) };
	}
	
	private String reassemble() {
		StringBuilder sb = new StringBuilder(NEWLINE);
		
		sb.append(packageDef).append(NEWLINE);
		sb.append(NEWLINE);
		sb.append(imports.toList().sort().join(NEWLINE));
		sb.append(NEWLINE).append(NEWLINE);
		sb.append(classDef.join(NEWLINE));
		sb.append(NEWLINE).append(NEWLINE);
		sb.append(constants.toList().sort().join(NEWLINE));
		sb.append(NEWLINE).append(NEWLINE);
		
		sb.append(constructorDef).append(NEWLINE);

		sb.append(createMethods.toList().sort().collect { it.toString() }.join(NEWLINE));
		
		sb.append(NEWLINE);
		sb.append("}").append(NEWLINE);
		
		return sb.toString();
	}

	private void load(File file) throws IOException {
		Deque<String> lines = new ArrayDeque<>(file.getText(encoding).split(NEWLINE) as List)

		consumePackage(lines);
		consumeImports(lines);
		consumeClass(lines);
		consumeConstants(lines);
		
		constructorDef = consumeConstructor(lines);
		
		String createMethodDef;
		while ((createMethodDef = consumeMethod(lines)) != null) {
			createMethods.add(new CreateMethod(createMethodDef));
		}
	}

	private void consumePackage(Deque<String> lines) {
		String l;
		while ((l = lines.pollFirst()) != null) {
			if (l.startsWith("package ")) {
				packageDef = l;
				break;
			}
		}
	}

	private void consumeImports(Deque<String> lines) {
		ignoreEmptyLines(lines);
		
		String l;
		while ((l = lines.pollFirst()) != null) {
			if (l.isEmpty()) {
				break;
			}
			if (l.startsWith("import ")) {
				imports.add(l);
			}
		}
	}
	
	private void consumeClass(Deque<String> lines) {
		classDef = new ArrayList<>();
		
		String l;
		while ((l = lines.pollFirst()) != null) {
			classDef.add(l);
			if (l.startsWith("public class")) {
				break;
			}
		}
	}

	private void consumeConstants(Deque<String> lines) {
		ignoreEmptyLines(lines);
		
		String l;
		while ((l = lines.pollFirst()) != null) {
			if (l.isEmpty()) {
				break;
			}
			if (!l.contains("static")) {
				lines.addFirst(l);
				break;
			}
			
			if (l.contains("QName")) {
				constants.add(l);
			}
		}
	}

	private String consumeMethod(Deque<String> lines) {
		ignoreEmptyLines(lines);
		
		List<String> methodLines = new ArrayList<>();

		// Handle class closing }
		String l = lines.pollFirst();
		if (l == null || l.length() < 2) {
			return null;
		}
		lines.addFirst(l);
		
		while ((l = lines.pollFirst()) != null) {
			methodLines.add(l);
			if (l.isEmpty()) {
				break;
			}
		}
		
		return methodLines.join(NEWLINE);
	}

	private String consumeConstructor(Deque<String> lines) {
		String constructorStr = consumeMethod(lines)

		// If it doesn't look like a constructor, push the lines back		
		if (!constructorStr.contains("ObjectFactory()")) {
			lines.addFirst("")
			constructorStr.split(NEWLINE).reverseEach { lines.addFirst(it) }
			constructorStr = ""
		}

		return constructorStr
	}

	private void ignoreEmptyLines(Deque<String> lines) {
		String l;
		while ((l = lines.pollFirst()) != null) {
			if (!l.trim().isEmpty()) {
				lines.addFirst(l);
				break;
			}
		}		
	}
	
	private static class CreateMethod implements Comparable<CreateMethod> {
		private String definition;
		private String uniqMethodName;
		
		CreateMethod(String definition) {
			this.definition = definition;
			
			String woPrefix = definition.replaceFirst("(?s).*public ", "")
			String woReturnType = woPrefix.replaceFirst("[^ ]+ ", "")
			String woBody = woReturnType.replaceFirst("(?s) .*", "");
			String methodName = woBody.replaceFirst("\\(.*", "");
			boolean isEmptyCreator = woBody.contains("()")
			uniqMethodName = isEmptyCreator ? "_" + methodName : methodName
		}

		@Override
		public int compareTo(CreateMethod o) {
			return uniqMethodName.compareTo(o.uniqMethodName);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((uniqMethodName == null) ? 0 : uniqMethodName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this.is(obj))
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CreateMethod other = (CreateMethod) obj;
			if (uniqMethodName == null) {
				if (other.uniqMethodName != null)
					return false;
			} else if (!uniqMethodName.equals(other.uniqMethodName))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return definition;
		}
	}
}
