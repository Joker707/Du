import org.kohsuke.args4j.*;

import java.io.File;
import java.io.IOException;

public class Parser {
    @Option(name = "-h", usage = "Usable format")
    private boolean usableFormat = false;
    @Option(name = "-c", usage = "Total size")
    private boolean totalSum = false;
    @Option(name = "--si", usage = "Sets other base")
    private boolean otherBase = false;
    @Argument(metaVar = "file", usage = "Input file")
    private String[] file;

    public static void main(String[] args) {
        new Parser().parse(args);
    }

    private void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Error");
            parser.printUsage(System.err);
        }
        Du du = new Du(usableFormat, totalSum, otherBase);
    }


}
