package stringconstant;

public class LoggerMessages {
   public static final String DONE = "Done: ";
   public static final String ERROR = "Error: ";
   public static final String WARNING = "Warning: ";

   public static final String SEPARATOR = "-----------------------------------------------------------------------------";

   public static final String ALL_OBJECTS_UPDATED = DONE + "All specified objects from test list were updated";
   public static final String OBJECTS_WAS_NOT_UPDATED = WARNING + "Objects was not updated: ";
   public static final String DUPLICATED_OBJRCTS_IN_TESTLIST = WARNING + "Test list contain duplicate objects. Check test list please!";
   public static final String NUMRER_UPDATED_OBJECTS_MORE_THEN_TEST_LIST = ERROR + "The number of updated objects is more then number of objects in test list";
   public static final String THERE_ARE = ERROR + " There are ";
   public static final String SCT_FILES_IN_DIRECTORY = "*.sct files in directory ";
   public static final String NO_SCT_FILES_IN_DIRECTORY_MUST_BE_ONE = ERROR + " There are no *.sct files in directory. Must be only one";
   public static final String MUST_BE_ONLY_ONE = ". Must be only one.";
   public static final String NO_FILES_IN_DIRECORY_FOLDER_MISSIING = ERROR + "There are no files in the directory, or specified folder are missing \n";
   public static final String FILE = DONE + "File ";
   public static final String FOUND = " was found ";
   public static final String NO_TARGET_XML_IN_DIRECTORY_FOLDER_MISSING = ERROR + "There are no file target.xml in the directory, or specified folder are missing \n";
   public static final String REFERENCE_FILE = DONE + "Reference file ";
   public static final String XML_FILES_IN_DIRECTORY = " *.xml files in directory ";
   public static final String NO_XML_FILES_IN_DIRECTORY_MUST_BE_ONE = ERROR + "There are no *.xml files. Must be only one.";
   public static final String NO_XML_FILES_IN_DIRECTORY_DIRECTORY_MISSING = ERROR + "There are no file *.xml in the directory, or specified folder are missing \n";
   public static final String LINE_READ_SUCCESSFULLY = DONE + "Line was successfully read";
   public static final String STRING_EMPTY = WARNING + "The string is empty";
   public static final String COULD_NOT_READ_LINE = ERROR + "Could not read line";
   public static final String COPIED_INTO = " was copied into ";
   public static final String DELETED = " was deleted";
   public static final String IMPOSSIBLE_COPY_FILE = ERROR + "Impossible copy file ";
   public static final String CREATED_INTO = " was created into ";
   public static final String IMPOSSIBLE_CREATE_REFERENCE = ERROR + "Impossible create reference ";
   public static final String INTO = " into ";
}
