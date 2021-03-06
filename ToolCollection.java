import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.io.IOException;
import java.lang.StackOverflowError;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.*;

public class ToolCollection {
    public static void main(String[] args) {
        try {
            Tools tool = new Tools();
            Recovery recovery = new Recovery();
            recovery.runAndRecover(tool);
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException in main()");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException in main()");
        } catch (Exception e) {
            System.out.println("Exception in main()");
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError in main()");
        } catch (Error e) {
            System.out.println("Error in main()");
        }
        main(args);
    }
}

class Tools implements Runnable, KeyListener {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()`~-_=+[{]};:',./<>?\"|";
    private static final String HEX_STRING = "0123456789abcdef";
    private static final String[] stopwords = {"exit", "Exit", "break", "Break", "stop", "Stop", "end", "End", "terminate", "Terminate"};
    private static final String[] helpwords = {"help", "Help", "functions", "Functions"};
    private static final String[] errorwords = {"debughash", "help", "functions", "exit", "break", "stop", "end", "terminate", "gethexstring", "systeminfo", "getos", "getpath", "leetify", "getrandomstring", "checktoken", "mineblock", "throwexception", "getrandomhash", "getepoch", "dec2hex", "bin2dec", "bin2hex", "clear", "hex2dec", "Clear", "dec2bin", "hex2bin", "directhash", "secretfunctions", "gpugrlc", "cpugrlc", "getstate", "setfalse", "bibbafy", "discordbibbafy", "base2dec", "base2hex", "base2bin", "base2base", "dec2base", "hex2base", "bin2base", "getcredits"};
    private static boolean stop = false;
    private static final String placeholder = System.getProperty("user.dir");
    private static final String path = placeholder.replaceAll("/", "\\");
    private static String salt;
    private static String salt2;
    private static String hash1;
    private static String finalHash;
    private static boolean gpugrlc = false;
    private static boolean cpugrlc = false;
    private static boolean lbl = false;
    private static boolean clearall = false;
    private static int zeros = 0;
    private static final CommieAI ai = new CommieAI();

    public Tools() {
    }

    private static void hashInit() {
        salt2 = salt2 + sha256(randomAlphaNumeric(64));
        salt2 = sha256(salt2);
        salt2 = salt2.substring(0, 15);
    }

    private static void debugInit() {
        String randomalp = randomAlphaNumeric(64);
        System.out.println("init rand: " + randomalp);
        salt2 = salt2 + sha256(randomalp);
        System.out.println(randomalp + " sha256()--> " + sha256(randomalp));
        salt2 = sha256(salt2);
        System.out.println(salt2 + " sha256()--> " + sha256(salt2));
        salt2 = salt2.substring(0, 15);
        System.out.println("Generated Salt: " + salt2);
    }

    private static void Execute() throws Exception {
        int x = -1;
        int y = -1;
        while (true) {
            while (true) {
                Scanner sc = new Scanner(System.in);
                String action = sc.nextLine();
                action = action.toLowerCase();
                if (action.contains("clear") && action.contains("-all"))
                    clearall = true;
                if (action.contains("-showlogo"))
                    clearall = false;
                String[] aiwords = action.split(" ");
                for (String keyword : aiwords) {
                    ai.listen(keyword);
                }
                if ((action.contains("clear"))) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    if (!clearall)
                        ASCIIDL.ASCII("YTool v0.1", false);
                } else if (action.contains("getrandomhash")) {
                    salt2 = salt2 + action;
                    hashInit();
                    System.out.println(getHash());
                } else if (action.contains("debughash")) {
                    salt2 = salt2 + action;
                    System.out.println("init:" + salt2);
                    debugInit();
                    System.out.println(getDebugHash());
                } else if (action.contains("mineblock")) {
                    if (action.contains("-lbl"))
                        lbl = true;
                    if (action.contains("-zeros"))
                        zeros = 1;
                    mineBlock();
                } else if (action.contains("secretfunctions")) {
                    gpugrlc = true;
                    cpugrlc = true;
                } else if (action.contains("getcredits")) {
                    System.out.println("Program by Yicheng Quan, MethodsDL and subclasses by Damian Lall");
                } else if (action.contains("base2dec")) {
                    String base2dec = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.base2dec(base2dec, base));
                } else if (action.contains("base2hex")) {
                    String base2hex = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.base2hex(base2hex, base));
                } else if (action.contains("base2bin")) {
                    String base2bin = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.base2bin(base2bin, base));
                } else if (action.contains("base2base")) {
                    String base2base = sc.nextLine();
                    int base = sc.nextInt();
                    int desiredbase = sc.nextInt();
                    System.out.println(literalHash.base2base(base2base, base, desiredbase));
                } else if (action.contains("dec2base")) {
                    BigInteger dec2base = new BigInteger(sc.nextLine());
                    int base = sc.nextInt();
                    System.out.println(literalHash.dec2base(dec2base, base));
                } else if (action.contains("hex2base")) {
                    String hex2base = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.hex2base(hex2base, base));
                } else if (action.contains("bin2base")) {
                    String bin2base = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.bin2base(bin2base, base));
                } else if (action.contains("gpugrlc") && gpugrlc) {
                    literalHash.gpugrlc();
                    gpugrlc = false;
                    if (!gpugrlc)
                        System.out.println("this.state=0");
                    else
                        System.out.println("this.state=1");
                    if (!cpugrlc)
                        System.out.println("cpu.state=0");
                    else
                        System.out.println("cpu.state=1");

                } else if (action.contains("bibbafy")) {
                    String bibbafy = sc.nextLine();
                    System.out.println(literalHash.bibbafy(bibbafy));
                } else if (action.contains("discordbibbafy")) {
                    String discordbibbafy = sc.nextLine();
                    System.out.println(literalHash.discordbibbafy(discordbibbafy));
                } else if (action.contains("cpugrlc") && cpugrlc) {
                    literalHash.cpugrlc();
                    cpugrlc = false;
                    if (!gpugrlc)
                        System.out.println("gpu.state=0");
                    else
                        System.out.println("gpu.state=1");
                    if (!cpugrlc)
                        System.out.println("this.state=0");
                    else
                        System.out.println("this.state=1");
                } else if (action.contains("getstate")) {
                    if (!gpugrlc)
                        System.out.println("gpu.state=0");
                    else
                        System.out.println("gpu.state=1");
                    if (!cpugrlc)
                        System.out.println("cpu.state=0");
                    else
                        System.out.println("cpu.state=1");
                } else if (action.contains("setfalse")) {
                    cpugrlc = false;
                    gpugrlc = false;
                } else if (action.contains("getepoch")) {
                    System.out.println(System.currentTimeMillis());
                } else if (action.contains("leetify")) {
                    String leetify = sc.nextLine();
                    System.out.println(literalHash.leetify(leetify));
                } else if (action.contains("getrandomstring")) {
                    int size = sc.nextInt();
                    System.out.println(randomAlphaNumeric(size));
                } else if (action.contains("throwexception")) {
                    throw new TestException("This was thrown at request for debug purposes, the auto recovery system should kick in.\nYou should be able to still type commands if the program did not encounter any unknown errors.\nTL:DR DON'T FREAK OUT THE PROGRAM RECOVERED, IT IS STILL RUNNING.");
                } else if (action.contains("dec2bin")) {
                    BigInteger dec2bin = new BigInteger(sc.nextLine());
                    System.out.println(literalHash.dec2bin(dec2bin));
                } else if (action.contains("hex2bin")) {
                    String hex2bin = sc.nextLine();
                    System.out.println(literalHash.hex2bin(hex2bin));
                } else if (action.contains("hex2dec")) {
                    String hex2dec = sc.nextLine();
                    System.out.println(literalHash.hex2dec(hex2dec));
                } else if (action.contains("directhash")) {
                    String literalhash = sc.nextLine();
                    System.out.println(literalHash.hash(literalhash));
                } else if (action.contains("dec2hex")) {
                    try {
                        BigInteger dec2hex = new BigInteger(sc.nextLine());
                        System.out.println(literalHash.dec2hex(dec2hex));
                    } catch (InputMismatchException e) {
                        System.out.println("Unrecognizable input");
                    }
                } else if (action.contains("bin2dec")) {
                    String bin2dec = sc.nextLine();
                    System.out.println(literalHash.bin2dec(bin2dec));
                } else if (action.contains("bin2hex")) {
                    String bin2hex = sc.nextLine();
                    System.out.println(literalHash.bin2hex(bin2hex));
                } else if (action.contains("checktoken")) {
                    System.out.println(checkToken());
                } else if (action.contains("getpath")) {
                    System.out.println("Working Directory = " + System.getProperty("user.dir"));
                } else if (action.contains("getos")) {
                    System.out.println(getOsName());
                } else if (action.contains("systeminfo")) {
                    getSystemInfo();
                } else if (action.contains("gethexstring")) {
                    int size = sc.nextInt();
                    System.out.println(randomHex(size));
                }
                if (Arrays.asList(stopwords).contains(action) || Arrays.asList(helpwords).contains(action) || action.contains("debughash") || action.contains("gethexstring") || action.contains("systeminfo") || action.contains("getos") || action.contains("getpath") || action.contains("leetify") || action.contains("getrandomstring") || action.contains("checktoken") || action.contains("mineblock") || action.contains("throwexception") || action.contains("getrandomhash") || action.contains("getepoch") || action.contains("dec2hex") || action.contains("bin2dec") || action.contains("bin2hex") || (action.contains("clear") || action.contains("hex2dec") || action.contains("Clear") || action.contains("dec2bin") || action.contains("hex2bin") || action.contains("directhash") || action.contains("secretfunctions") || action.contains("gpugrlc") || action.contains("cpugrlc") || action.contains("getstate") || action.contains("setfalse") || action.contains("bibbafy") || action.contains("discordbibbafy") || action.contains("base2dec") || action.contains("base2hex") || action.contains("base2bin") || action.contains("base2base") || action.contains("dec2base") || action.contains("hex2base") || action.contains("bin2base")) || action.contains("getcredits")) {
                    if (Arrays.asList(helpwords).contains(action)) {
                        System.out.println("help-help");
                        System.out.println("functions-help");
                        System.out.println("break-stops program");
                        System.out.println("stop-stops program");
                        System.out.println("end-stops program");
                        System.out.println("terminate-stops program");
                        System.out.println("dec2bin-converts decimal to binary");
                        System.out.println("dec2hex-converts an integer to a hex string");
                        System.out.println("dec2base-converts a decimal number to a number of any given base");
                        System.out.println("hex2bin-converts a hex string to binary");
                        System.out.println("hex2dec-converts a hex string to decimal number");
                        System.out.println("hex2base-converts a hex string to a number of any given base");
                        System.out.println("bin2dec-converts a binary string to decimal number");
                        System.out.println("bin2hex-converts a binary string to a hexadecimal string");
                        System.out.println("bin2base-converts a number of any base into a base 2 binary string");
                        System.out.println("bin2base-converts a binary string to a number of any given base");
                        System.out.println("base2base-converts a number of any base to another number of any base given <currentbase> <desiredbase>");
                        System.out.println("base2dec-converts a number of any base into a base 10 number");
                        System.out.println("base2hex-converts a number of any base into a base 16 hex string");
                        System.out.println("directhash-gives the sha256 hash of a given string");
                        System.out.println("getepoch-gets current UNIX epoch time");
                        System.out.println("getrandomhash-gets a securely generated random sha-256 hash");
                        System.out.println("throwexception-throws a new 'TestException' to test recovery system");
                        System.out.println("getrandomstring-creates a random alpha-numeric string given a length");
                        System.out.println("getrandomhex-creates a random hex string given a length");
                        System.out.println("leetify-converts words using traditional english characters into mainstream leet");
                        System.out.println("bibbafy-adds them hard Bs to your text");
                        System.out.println("discordbibbafy-optimized for discord :wink:");
                        System.out.println("getcredits-gets the contributors or sources for this program");
                        System.out.println("mineblock-finds a valid sha256 hash given int diff<65");
                        System.out.println("checktoken-clears payment of clm given <validHash><target><diff><target><time><token> in order");
                        System.out.println("getpath-displays the path where the program was initialized");
                        System.out.println("getos-gives os name and architecture");
                        System.out.println("systeminfo-gives system info");
                        System.out.println("debughash-shows debug info about getrandomhash");
                    }
                } else {
                    System.out.println("'" + action + "'" + " does not seem to exist, to see a list of commands, type 'help'.");
                    if (!action.equals("")) {
                        System.out.println("Suggestions: ");
                        for (String i : errorwords)
                            if (i.contains(action))
                                System.out.println(i);
                    }
                }
                if (Arrays.asList(stopwords).contains(action)) {
                    System.out.println("Are you sure? [Y/N]");
                    char stop = sc.nextLine().charAt(0);
                    switch (stop) {
                        case 'y':
                        case 'Y':
                            System.out.println("Closing Program...");
                            Thread.sleep(512);
                            System.exit(0);
                            break;
                        case 'n':
                        case 'N':
                            x = -1;
                            break;
                        default:
                            continue;
                    }
                }
            }
        }
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private static String randomHex(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * HEX_STRING.length());
            builder.append(HEX_STRING.charAt(character));
        }
        return builder.toString();
    }

    private static String getNewSalt(String oldSalt) {
        String randomSalt = sha256("" + randomAlphaNumeric(256));
        randomSalt = randomSalt.substring(0, 15);
        String newSalt = sha256(oldSalt + randomSalt);
        newSalt = newSalt.substring(0, 15);
        return newSalt;
    }

    private static String getDebugNewSalt(String oldSalt) {
        String rand = randomAlphaNumeric(256);
        System.out.println("randinit: " + rand);
        String randomSalt = sha256("" + rand);
        randomSalt = randomSalt.substring(0, 15);
        System.out.println("Generated Salt: " + randomSalt);
        String newSalt = sha256(oldSalt + randomSalt);
        System.out.println(oldSalt + randomSalt + " sha256()--> " + sha256(oldSalt + randomSalt));
        newSalt = newSalt.substring(0, 15);
        System.out.println("Generated Salt: " + newSalt);
        return newSalt;
    }

    private static String getHash() {
        salt = sha256(getBase());
        salt = salt.substring(0, 15);
        hash1 = sha256(salt2 + salt + getBase() + salt2);
        salt = getNewSalt(salt);
        salt2 = getNewSalt(salt2);
        finalHash = sha256(salt2 + salt + hash1 + sha256(getBase() + salt + salt2));
        salt = getNewSalt(salt);
        salt2 = getNewSalt(salt2);
        finalHash = sha256(sha256(salt2 + finalHash + salt + hash1 + salt2));
        return finalHash;
    }

    private static String getDebugHash() {
        String base = getBase();
        System.out.println("Base: " + base);
        salt = sha256(base);
        System.out.println(base + " sha256()--> " + sha256(base));
        salt = salt.substring(0, 15);
        System.out.println("Generated Salt: " + salt);
        hash1 = sha256(salt2 + salt + getBase() + salt2);
        System.out.println(salt2 + salt + getBase() + salt2 + " sha256()--> " + sha256(salt2 + salt + getBase() + salt2));
        salt = getDebugNewSalt(salt);
        salt2 = getDebugNewSalt(salt2);
        finalHash = sha256(salt2 + salt + hash1 + sha256(getBase() + salt + salt2));
        System.out.println(salt2 + salt + hash1 + sha256(getBase() + salt + salt2) + " sha256()--> " + salt2 + salt + hash1 + sha256(getBase() + salt + salt2));
        salt = getDebugNewSalt(salt);
        salt2 = getDebugNewSalt(salt2);
        finalHash = sha256(sha256(salt2 + finalHash + salt + hash1 + salt2));
        System.out.println(sha256(salt2 + finalHash + salt + hash1 + salt2) + " sha256()--> " + sha256(sha256(salt2 + finalHash + salt + hash1 + salt2)));
        return finalHash;
    }

    private static String getBase() {
        return randomAlphaNumeric(64);
    }

    private static boolean isProcessed(String check) {
        if (check.length() != 64 && check.length() != 128)
            return false;
        Scanner scanner = new Scanner("processedtx.txt");

        //now read the file line by line...
        int lineNum = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineNum++;
            if (line.contains(check)) {
                System.out.println("Entry is already processed entry no. " + lineNum);
                return true;
            }
        }
        return false;
    }

    private static void mineBlock() throws InterruptedException, IOException, AssetGetFailException {
        String zero = "0000000000000000000000000000000000000000000000000000000000000000";
        String target;
        DecimalFormat df = new DecimalFormat("#.###");
        Scanner sc = new Scanner(System.in);
        int diff = sc.nextInt();
        int mRange = diff + (int) (Math.random() * (64 - diff));
        int ct = 0;
        long prevTime;
        if (diff > 64)
            diff = 64;
        hashInit();
        if (zeros == 1) {
            target = zero.substring((zero.length() - 1) - diff + 1);
        } else
            target = getHash().substring(mRange - diff, mRange);
        String current = getHash();
        long startTime = System.currentTimeMillis();
        long callTime = startTime;
        long timeDiff = 0;
        long hashesps = 0;
        long kh, mh, gh, th, ph, eh;
        kh = 1000;
        mh = kh * 1000;
        gh = mh * 1000;
        th = gh * 1000;
        ph = th * 1000;
        eh = ph * 1000;
        prevTime = timeDiff;
        boolean refreshScreen = false;
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        switch (zeros) {
            case 0:
                while (!(current.contains(target)) && !stop) {
                    ct++;
                    if ((timeDiff - prevTime) > 256) {
                        hashesps = (ct / (timeDiff / 1000));
                        refreshScreen = true;
                        System.out.print("Target: " + target + " Result: " + current + " Diff: " + literalHash.dec2hex(new BigInteger(diff + "")));
                    }
                    hashInit();
                    current = getHash();
                    timeDiff = (new Date()).getTime() - startTime;
                    if ((timeDiff / 1000) < 1)
                        timeDiff = 1000;
                    if ((refreshScreen)) {
                        if (hashesps > kh && !(hashesps > mh)) {
                            System.out.print(" KH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / kh));
                        } else if (hashesps > mh && !(hashesps > gh)) {
                            System.out.print(" MH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / mh));
                        } else if (hashesps > gh && (hashesps > th)) {
                            System.out.print(" GH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / gh));
                        } else if (hashesps > th && !(hashesps > ph)) {
                            System.out.print(" TH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / th));
                        } else if (hashesps > ph && !(hashesps > eh)) {
                            System.out.print(" PH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / ph));
                        } else if (hashesps > eh) {
                            System.out.print(" EH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / eh));
                        } else
                            System.out.print(" H/s: " + (ct / (timeDiff / 1000)));
                        if (!lbl) {
                            System.out.print((char) 13);
                        } else
                            System.out.println();
                        refreshScreen = false;
                    }
                    if ((timeDiff / 1000) / 60 >= 2) {
                        ct = 0;
                        startTime = System.currentTimeMillis();
                    }
                }
                break;
            case 1:
                while (!(current.substring(0, diff).equals(target)) && !stop) {
                    ct++;
                    if ((timeDiff - prevTime) > 256) {
                        hashesps = (ct / (timeDiff / 1000));
                        refreshScreen = true;
                        System.out.print("Target: " + target + " Result: " + current + " Diff: " + literalHash.dec2hex(new BigInteger(diff + "")));
                    }
                    hashInit();
                    current = getHash();
                    timeDiff = (new Date()).getTime() - startTime;
                    if ((timeDiff / 1000) < 1)
                        timeDiff = 1000;
                    if ((refreshScreen)) {
                        if (hashesps > kh && !(hashesps > mh)) {
                            System.out.print(" KH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / kh));
                        } else if (hashesps > mh && !(hashesps > gh)) {
                            System.out.print(" MH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / mh));
                        } else if (hashesps > gh && (hashesps > th)) {
                            System.out.print(" GH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / gh));
                        } else if (hashesps > th && !(hashesps > ph)) {
                            System.out.print(" TH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / th));
                        } else if (hashesps > ph && !(hashesps > eh)) {
                            System.out.print(" PH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / ph));
                        } else if (hashesps > eh) {
                            System.out.print(" EH/s: " + df.format(((double) ct / (double) (timeDiff / 1000)) / eh));
                        } else
                            System.out.print(" H/s: " + (ct / (timeDiff / 1000)));
                        if (!lbl) {
                            System.out.print((char) 13);
                        } else
                            System.out.println();
                        refreshScreen = false;
                    }
                    if ((timeDiff / 1000) / 60 >= 2) {
                        ct = 0;
                        startTime = System.currentTimeMillis();
                    }
                }
        }
        if (!stop) {
            double timeMins = ((((double) System.currentTimeMillis() - (double) callTime) / (double) 1000) / (double) 60);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            try {
                loadingBar();
            } catch (Exception e) {
                throw new AssetGetFailException("UI Failed to Load");
            }
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("Valid Hash: " + current + " Target: " + target + " Diff: " + literalHash.dec2hex(new BigInteger(diff + "")) + " Time until solution in minutes: " + df.format(timeMins));
            if (zeros == 1)
                System.out.println("CLM Owed: " + (((double) diff / ((double) 64 / (double) diff)) * (diff * ((((double) System.currentTimeMillis() - (double) callTime) / (double) 1000) / (double) 60))) + "\n");
            else
                System.out.println("CLM Owed: " + (((double) diff / (double) 64) * (diff * ((((double) System.currentTimeMillis() - (double) callTime) / (double) 1000) / (double) 60))) + "\n");
            System.out.println("Payment Token: " + sha256(target).substring(0, 33) + sha256(current.substring(0, 33) + sha256(diff + "") + sha256(df.format(timeMins) + "") + current.substring(33)) + sha256(target).substring(33) + " Do not lose this or your CLM cannot be rewarded.");
            //(Diff/64(Diff*ElapsedTime)) = CLM Owed
            /*
             */
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\pendingtx.txt", true))) {
                bw.write(current + " " + target + " " + literalHash.dec2hex(new BigInteger(diff + "")) + " " + df.format(timeMins) + " ");
                bw.write(sha256(target).substring(0, 33) + sha256(current.substring(0, 33) + sha256(diff + "") + sha256(df.format(timeMins) + "") + current.substring(33)) + sha256(target).substring(33));
                bw.newLine();
                bw.write("CLM Owed: " + (((double) diff / (double) 64) * (diff * ((((double) System.currentTimeMillis() - (double) callTime) / (double) 1000) / (double) 60))) + "\n");
                bw.newLine();
                bw.newLine();
                // no need to close it.
                //bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            lbl = false;
            zeros = 0;
        }

    }

    private static boolean checkToken() {
        System.out.println("<hash(String)><diff(int)><target(String)><time(double)><token(String)>");
        Scanner sc = new Scanner(System.in);
        String validHash = sc.nextLine();
        int diff = sc.nextInt();
        sc.nextLine();
        String target = sc.nextLine();
        double time = sc.nextDouble();
        String token = sc.nextLine();
        token = sc.nextLine();
        if (validHash.length() != 64) {
            System.out.println("short/long hash");
            return false;
        }
        if (token.length() != 128) {
            System.out.println("short/long token");
            return false;
        }
        if (target.length() < diff) {
            System.out.println("Invalid target given diff");
            return false;
        }
        if (isProcessed(validHash) || isProcessed(token)) {
            System.out.println("already processed");
            return true;
        }

        if (!(isProcessed(validHash) || isProcessed(token)) && token.equals(sha256(target).substring(0, 33) + sha256(validHash.substring(0, 33) + sha256(diff + "") + sha256(time + "") + validHash.substring(33)) + sha256(target).substring(33))) {
            try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(path + "\\processedtx.txt", true))) {
                bw1.write(validHash + " " + token);
                bw1.newLine();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static void loadingBar() throws Exception {
        long time;
        int percent = 0;
        char[] bar = new char[(int) ((Math.random() * 75) + 75)];
        bar[0] = '|';
        bar[bar.length - 1] = '|';
        for (int i = 1; i < bar.length - 1; i++) {
            bar[i] = ' ';
        }
        for (int i = 1; i < bar.length - 1; i++) {
            percent = (int) (i * (100.0 / (double) bar.length));
            if (bar[i] == ' ')
                bar[i] = '>';
            System.out.print(percent);
            System.out.print(new String(bar) + "Loading UI asset: " + getNewSalt(""));
            time = (long) (((Math.random() * (512 / Runtime.getRuntime().availableProcessors()))) + 1);
            long start = System.currentTimeMillis();
            while (start >= System.currentTimeMillis() - time) ; // do nothing
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        }

    }

    private static String getOs() {
        return System.getProperty("os.name");
    }

    private static String getArchitecture() {
        return System.getProperty("os.arch");
    }

    private static String getOsName() {
        String architecture = getArchitecture();
        return getOs() + " " + architecture;
    }

    private static void getSystemInfo() {
        System.out.println("Available processors (cores): " +
                Runtime.getRuntime().availableProcessors());

        System.out.println("Free memory (bytes): " +
                Runtime.getRuntime().freeMemory());

        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Maximum memory (bytes): " +
                (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

        System.out.println("Total memory available to JVM (bytes): " +
                Runtime.getRuntime().totalMemory());

        File[] roots = File.listRoots();

        for (File root : roots) {
            System.out.println("File system root: " + root.getAbsolutePath());
            System.out.println("Total space (bytes): " + root.getTotalSpace());
            System.out.println("Free space (bytes): " + root.getFreeSpace());
            System.out.println("Usable space (bytes): " + root.getUsableSpace());
        }
    }

    @Override
    public void run() {
        try {
            ASCIIDL.ASCII("YTool v0.1", false);
            Execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e) {
        boolean s = false;
        boolean ctrl = false;
        if (e.getKeyCode() == KeyEvent.VK_S)
            s = true;
        else if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            ctrl = true;

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        stop = false;
    }
}

class literalHash {
    private final static String list = "GHIJKLMNOPQRSTUVWXYZghijklmnopqrstuvwxyz!@#$%^&*()`~-_=+[{]};:',./<>?\"|";
    private static final char[] chararray = list.toCharArray();
    private static final List<Character> nonAcceptableHex = new ArrayList<Character>();
    private static String bin = "";

    public static String hash(String base) {
        return Tools.sha256(base);
    }

    public static BigInteger hex2dec(String hex) {
        for (char i : chararray) {
            nonAcceptableHex.add(i);
        }
        char[] contents = hex.toCharArray();
        for (int i = 0; i < contents.length; i++) {
            if (nonAcceptableHex.contains(contents[i])) {
                System.out.println("non hex characters detected");
                return new BigInteger("-1");
            }
        }
        BigInteger bi = new BigInteger(hex, 16);
        return bi;
    }

    public static String dec2hex(BigInteger base) {
        try {
            return base.toString(16);
        } catch (Exception e) {
            return "Unrecognizable input";
        }

    }

    public static String dec2bin(BigInteger dec) {
        try {
            if (dec.compareTo(BigInteger.ONE) == -1 || dec.compareTo(BigInteger.ONE) == 0)
                bin = bin + dec.mod(BigInteger.valueOf(2));
            else {
                bin = bin + dec2bin(dec.divide(BigInteger.valueOf(2)));
                bin = bin + dec.mod(BigInteger.valueOf(2));
            }
            String temp = bin;
            bin = "";
            return temp;
        } catch (Exception e) {
            return "Unrecognizable input";
        }
    }

    public static String bin2dec(String bin) {
        String res = "";
        char[] binary = bin.toCharArray();
        BigInteger decimal = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(2);
        for (int j = binary.length - 1; j >= 0; j--) {
            if (binary[j] == '1')
                decimal = decimal.add((base.pow((binary.length - j) - 1)));
            else if (binary[j] != '1' && binary[j] != '0') {
                decimal = new BigInteger("-1");
                res = "this result is wrong, as you used a non-binary number";
                return res;
            }
        }
        return res + decimal;
    }

    public static String bin2hex(String binary) {
        try {
            String res = "";
            BigInteger decimal = new BigInteger(bin2dec(binary));
            return dec2hex(decimal);
        } catch (Exception e) {
            return "Unrecognizable input";
        }

    }

    public static String hex2bin(String hex) {
        for (char i : chararray) {
            nonAcceptableHex.add(i);
        }
        char[] contents = hex.toCharArray();
        for (int i = 0; i < contents.length; i++) {
            if (nonAcceptableHex.contains(contents[i])) {
                return "non hex characters detected";
            }
        }
        BigInteger dec = hex2dec(hex);
        String binary = dec2bin(dec);
        String standin = binary;
        binary = "";
        return standin;
    }

    public static BigInteger base2dec(String number, int base) {
        BigInteger bi = new BigInteger(number, base);
        return bi;
    }

    public static String base2hex(String number, int base) {
        BigInteger bi = base2dec(number, base);
        return dec2hex(bi);
    }

    public static String base2bin(String number, int base) {
        BigInteger bi = new BigInteger(number, base);
        return dec2bin(bi);
    }

    public static String base2base(String number, int base, int desiredbase) {
        BigInteger bi = new BigInteger(number, base);
        return bi.toString(desiredbase);
    }

    public static String dec2base(BigInteger bi, int desiredbase) {
        return bi.toString(desiredbase);
    }

    public static String hex2base(String hex, int desiredbase) {
        BigInteger bi = new BigInteger(hex, 16);
        return bi.toString(desiredbase);
    }

    public static String bin2base(String bin, int desiredbase) {
        BigInteger bi = new BigInteger(bin2dec(bin));
        return bi.toString(desiredbase);
    }

    public static String leetify(String inputs) {
        String edit = inputs;
        char[] editchar = edit.toCharArray();
        for (int i = 0; i < editchar.length; i++) {
            if (editchar[i] == 'a' || editchar[i] == 'A') {
                editchar[i] = '4';
            } else if (editchar[i] == 'b' || editchar[i] == 'B') {
                editchar[i] = '8';
            } else if (editchar[i] == 'i' || editchar[i] == 'I') {
                editchar[i] = '1';
            } else if (editchar[i] == 'q' || editchar[i] == 'Q') {
                editchar[i] = '9';
            } else if (editchar[i] == 's' || editchar[i] == 'S') {
                editchar[i] = '5';
            } else if (editchar[i] == 'z' || editchar[i] == 'Z') {
                editchar[i] = '2';
            } else if (editchar[i] == 'e' || editchar[i] == 'E') {
                editchar[i] = '3';
            } else if (editchar[i] == 'g' || editchar[i] == 'G') {
                editchar[i] = '6';
            } else if (editchar[i] == 'o' || editchar[i] == 'O') {
                editchar[i] = '0';
            }
        }
        edit = new String(editchar);
        return edit;
    }

    public static String bibbafy(String inputs) {
        String edit = inputs;
        char[] editchar = edit.toCharArray();
        for (int i = 0; i < editchar.length; i++) {
            if (editchar[i] == 'b') {
                editchar[i] = 'B';
            }
        }
        edit = new String(editchar);
        return edit;
    }

    public static String discordbibbafy(String inputs) {
        inputs = inputs.replaceAll("B", "b");
        return inputs = inputs.replaceAll("b", ":b:");
    }

    public static void gpugrlc() {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", "Garlic.bat");
            File dir = new File("C:\\Users\\yquan\\Desktop\\ccminer");
            pb.directory(dir);
            Process p = pb.start();
        } catch (IOException e) {
            System.out.println("Program failed to start");
        }
    }

    public static void cpugrlc() {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", "Garlic.bat");
            File dir = new File("C:\\Users\\yquan\\Desktop\\cpuminer-opt-3.8.3.1-windows");
            pb.directory(dir);
            Process p = pb.start();
        } catch (IOException e) {
            System.out.println("Program failed to start");
        }
    }

}

class Recovery {

    private final Object lock = new Object();

    void runAndRecover(final Runnable runnable) { // Will pass an instance of Tools
        while (true) {
            // Synchronising to make sure that only one instance
            // of runnable input is running at the time.
            synchronized (lock) {
                try {
                    runnable.run();
                    // Ideally the process should run forever
                    // (no termination),
                    // and the code of THIS FILE will be blocked HERE
                    // with the runnable RUNNING (which is what we want).
                } catch (Exception exception) {
                    // If the process was interrupted
                    // by uncaught exception,
                    // we will restart it.
                    lock.notifyAll();
                }
            }
        }
    }

}

class TestException extends Exception {
    public TestException(String s) {
        // Call constructor of parent Exception
        super(s);
    }
}

class AssetGetFailException extends Exception {
    public AssetGetFailException(String s) {
        // Call constructor of parent Exception
        super(s);
    }
}

class MethodsDL {

    public static void main(String[] args) {
        System.out.println();
    }

    static void Delay(int delayTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(delayTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(MethodsDL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class ASCIIDL extends MethodsDL {
    private static String level1 = "";
    private static String level2 = "";
    private static String level3 = "";
    private static String level4 = "";
    private static String level5 = "";
    private static String level6 = "";

    public static void ASCII(String text, boolean slow) {
        char[] textArray = text.toUpperCase().toCharArray();

        level1 = "";
        level2 = "";
        level3 = "";
        level4 = "";
        level5 = "";
        level6 = "";
        for (int i = 0; i < textArray.length; i++) {
            switch (textArray[i]) {
                case '0':
                    drawZero();
                    break;
                case '1':
                    drawOne();
                    break;
                case '2':
                    drawTwo();
                    break;
                case '3':
                    drawThree();
                    break;
                case '4':
                    drawFour();
                    break;
                case '5':
                    drawFive();
                    break;
                case '6':
                    drawSix();
                    break;
                case '7':
                    drawSeven();
                    break;
                case '8':
                    drawEight();
                    break;
                case '9':
                    drawNine();
                    break;
                case 'A':
                    drawA();
                    break;
                case 'B':
                    drawB();
                    break;
                case 'C':
                    drawC();
                    break;
                case 'D':
                    drawD();
                    break;
                case 'E':
                    drawE();
                    break;
                case 'F':
                    drawF();
                    break;
                case 'G':
                    drawG();
                    break;
                case 'H':
                    drawH();
                    break;
                case 'I':
                    drawI();
                    break;
                case 'J':
                    drawJ();
                    break;
                case 'K':
                    drawK();
                    break;
                case 'L':
                    drawL();
                    break;
                case 'M':
                    drawM();
                    break;
                case 'N':
                    drawN();
                    break;
                case 'O':
                    drawO();
                    break;
                case 'P':
                    drawP();
                    break;
                case 'Q':
                    drawQ();
                    break;
                case 'R':
                    drawR();
                    break;
                case 'S':
                    drawS();
                    break;
                case 'T':
                    drawT();
                    break;
                case 'U':
                    drawU();
                    break;
                case 'V':
                    drawV();
                    break;
                case 'W':
                    drawW();
                    break;
                case 'X':
                    drawX();
                    break;
                case 'Y':
                    drawY();
                    break;
                case 'Z':
                    drawZ();
                    break;
                case ' ':
                    drawSpace();
                    break;
                case '.':
                    drawPeriod();
                    break;
                case '!':
                    drawExclamation();
                    break;
                case '?':
                    drawQuestion();
                    break;
                case ',':
                    drawComma();
                    break;
                case '\'':
                    drawApostrophe();
                    break;
                case '=':
                    drawEquals();
                    break;
                case '<':
                    drawLess();
                    break;
                case '>':
                    drawMore();
                    break;
                case '/':
                    drawForward();
                    break;
                case '\\':
                    drawBack();
                    break;
                case ':':
                    drawColon();
                    break;
                case ';':
                    drawSemicolon();
                    break;
                case '(':
                    drawOpenParenthesis();
                    break;
                case ')':
                    drawCloseParenthesis();
                    break;
                case '"':
                    drawQuotes();
                    break;
                case '@':
                    drawAt();
                    break;
                case '#':
                    drawNumber();
                    break;
                case '$':
                    drawDollar();
                    break;
                case '%':
                    drawPercent();
                    break;
                case '^':
                    drawUp();
                    break;
                case '&':
                    drawAnd();
                    break;
                case '*':
                    drawAsterisk();
                    break;
                case '+':
                case '-':
                case '[':
                case ']':
                default:
                    System.out.println("Error - Invalid Character");
            }
        }
        if (level1.length() > 79) {
            level1 = level1.substring(0, 79);
            level2 = level2.substring(0, 79);
            level3 = level3.substring(0, 79);
            level4 = level4.substring(0, 79);
            level5 = level5.substring(0, 79);
            level6 = level6.substring(0, 79);
        }
        if (slow) {
            Delay(200);
            System.out.println(level1);
            Delay(200);
            System.out.println(level2);
            Delay(200);
            System.out.println(level3);
            Delay(200);
            System.out.println(level4);
            Delay(200);
            System.out.println(level5);
            Delay(200);
            System.out.println(level6);
        } else {
            System.out.println(level1);
            System.out.println(level2);
            System.out.println(level3);
            System.out.println(level4);
            System.out.println(level5);
            System.out.println(level6);
        }
    }

    private static void drawZero() {
        level1 += "  ____  ";
        level2 += " / __ \\ ";
        level3 += "| | /| |";
        level4 += "| |/ | |";
        level5 += "| |__| |";
        level6 += " \\____/ ";
    }

    private static void drawOne() {
        level1 += " __ ";
        level2 += "/_ |";
        level3 += " | |";
        level4 += " | |";
        level5 += " | |";
        level6 += " |_|";
    }

    private static void drawTwo() {
        level1 += " ___  ";
        level2 += "|__ \\ ";
        level3 += "   ) |";
        level4 += "  / / ";
        level5 += " / /_ ";
        level6 += "|____|";
    }

    private static void drawThree() {
        level1 += " ____  ";
        level2 += "|___ \\ ";
        level3 += "  __) |";
        level4 += " |__ < ";
        level5 += " ___) |";
        level6 += "|____/ ";
    }

    private static void drawFour() {
        level1 += " _  _   ";
        level2 += "| || |  ";
        level3 += "| || |_ ";
        level4 += "|__   _|";
        level5 += "   | |  ";
        level6 += "   |_|  ";
    }

    private static void drawFive() {
        level1 += " _____ ";
        level2 += "| ____|";
        level3 += "| |__  ";
        level4 += "|___ \\ ";
        level5 += " ___) |";
        level6 += "|____/ ";
    }

    private static void drawSix() {
        level1 += "   __  ";
        level2 += "  / /  ";
        level3 += " / /_  ";
        level4 += "| '_ \\ ";
        level5 += "| (_) |";
        level6 += " \\___/ ";
    }

    private static void drawSeven() {
        level1 += " ______ ";
        level2 += "|____  |";
        level3 += "    / / ";
        level4 += "   / /  ";
        level5 += "  / /   ";
        level6 += " /_/    ";
    }

    private static void drawEight() {
        level1 += "  ___  ";
        level2 += " / _ \\ ";
        level3 += "| (_) |";
        level4 += " > _ < ";
        level5 += "| (_) |";
        level6 += " \\___/ ";
    }

    private static void drawNine() {
        level1 += "  ___  ";
        level2 += " / _ \\ ";
        level3 += "| (_) |";
        level4 += " \\__, |";
        level5 += "   / / ";
        level6 += "  /_/  ";
    }

    private static void drawA() {
        level1 += "          ";
        level2 += "    /\\    ";
        level3 += "   /  \\   ";
        level4 += "  / /\\ \\  ";
        level5 += " / ____ \\ ";
        level6 += "/_/    \\_\\";
    }

    private static void drawB() {
        level1 += " ____  ";
        level2 += "|  _ \\ ";
        level3 += "| |_) |";
        level4 += "|  _ < ";
        level5 += "| |_) |";
        level6 += "|____/ ";
    }

    private static void drawC() {
        level1 += "  _____ ";
        level2 += " / ____|";
        level3 += "| |     ";
        level4 += "| |     ";
        level5 += "| |____ ";
        level6 += " \\_____|";
    }

    private static void drawD() {
        level1 += " _____  ";
        level2 += "|  __ \\ ";
        level3 += "| |  | |";
        level4 += "| |  | |";
        level5 += "| |__| |";
        level6 += "|_____/ ";
    }

    private static void drawE() {
        level1 += " ______ ";
        level2 += "|  ____|";
        level3 += "| |__   ";
        level4 += "|  __|  ";
        level5 += "| |____ ";
        level6 += "|______|";
    }

    private static void drawF() {
        level1 += " ______ ";
        level2 += "|  ____|";
        level3 += "| |__   ";
        level4 += "|  __|  ";
        level5 += "| |     ";
        level6 += "|_|     ";
    }

    private static void drawG() {
        level1 += "  _____ ";
        level2 += " / ____|";
        level3 += "| |  __ ";
        level4 += "| | |_ |";
        level5 += "| |__| |";
        level6 += " \\_____|";
    }

    private static void drawH() {
        level1 += " _    _ ";
        level2 += "| |  | |";
        level3 += "| |__| |";
        level4 += "|  __  |";
        level5 += "| |  | |";
        level6 += "|_|  |_|";
    }

    private static void drawI() {
        level1 += " _____ ";
        level2 += "|_   _|";
        level3 += "  | |  ";
        level4 += "  | |  ";
        level5 += " _| |_ ";
        level6 += "|_____|";
    }

    private static void drawJ() {
        level1 += "      _ ";
        level2 += "     | |";
        level3 += "     | |";
        level4 += " _   | |";
        level5 += "| |__| |";
        level6 += " \\____/ ";
    }

    private static void drawK() {
        level1 += " _  __";
        level2 += "| |/ /";
        level3 += "| ' / ";
        level4 += "|  <  ";
        level5 += "| . \\ ";
        level6 += "|_|\\_\\";
    }

    private static void drawL() {
        level1 += " _      ";
        level2 += "| |     ";
        level3 += "| |     ";
        level4 += "| |     ";
        level5 += "| |____ ";
        level6 += "|______|";
    }

    private static void drawM() {
        level1 += " __  __ ";
        level2 += "|  \\/  |";
        level3 += "| \\  / |";
        level4 += "| |\\/| |";
        level5 += "| |  | |";
        level6 += "|_|  |_|";
    }

    private static void drawN() {
        level1 += " _   _ ";
        level2 += "| \\ | |";
        level3 += "|  \\| |";
        level4 += "| . ` |";
        level5 += "| |\\  |";
        level6 += "|_| \\_|";
    }

    private static void drawO() {
        level1 += "  ____  ";
        level2 += " / __ \\ ";
        level3 += "| |  | |";
        level4 += "| |  | |";
        level5 += "| |__| |";
        level6 += " \\____/ ";
    }

    private static void drawP() {
        level1 += " _____  ";
        level2 += "|  __ \\ ";
        level3 += "| |__) |";
        level4 += "|  ___/ ";
        level5 += "| |     ";
        level6 += "|_|     ";
    }

    private static void drawQ() {
        level1 += "  ____  ";
        level2 += " / __ \\ ";
        level3 += "| |  | |";
        level4 += "| |  | |";
        level5 += "| |__| |";
        level6 += " \\___\\_\\";
    }

    private static void drawR() {
        level1 += " _____  ";
        level2 += "|  __ \\ ";
        level3 += "| |__) |";
        level4 += "|  _  / ";
        level5 += "| | \\ \\ ";
        level6 += "|_|  \\_\\";
    }

    private static void drawS() {
        level1 += "  _____ ";
        level2 += " / ____|";
        level3 += "| (___  ";
        level4 += " \\___ \\ ";
        level5 += " ____) |";
        level6 += "|_____/ ";
    }

    private static void drawT() {
        level1 += " _______ ";
        level2 += "|__   __|";
        level3 += "   | |   ";
        level4 += "   | |   ";
        level5 += "   | |   ";
        level6 += "   |_|   ";
    }

    private static void drawU() {
        level1 += " _    _ ";
        level2 += "| |  | |";
        level3 += "| |  | |";
        level4 += "| |  | |";
        level5 += "| |__| |";
        level6 += " \\____/ ";
    }

    private static void drawV() {
        level1 += "__      __";
        level2 += "\\ \\    / /";
        level3 += " \\ \\  / / ";
        level4 += "  \\ \\/ /  ";
        level5 += "   \\  /   ";
        level6 += "    \\/    ";
    }

    private static void drawW() {
        level1 += "__          __";
        level2 += "\\ \\        / /";
        level3 += " \\ \\  /\\  / / ";
        level4 += "  \\ \\/  \\/ /  ";
        level5 += "   \\  /\\  /   ";
        level6 += "    \\/  \\/    ";
    }

    private static void drawX() {
        level1 += "__   __";
        level2 += "\\ \\ / /";
        level3 += " \\ ' / ";
        level4 += "  > <  ";
        level5 += " / . \\ ";
        level6 += "/_/ \\_\\";
    }

    private static void drawY() {
        level1 += "__     __";
        level2 += "\\ \\   / /";
        level3 += " \\ \\_/ / ";
        level4 += "  \\   /  ";
        level5 += "   | |   ";
        level6 += "   |_|   ";
    }

    private static void drawZ() {
        level1 += " ______";
        level2 += "|___  /";
        level3 += "   / / ";
        level4 += "  / /  ";
        level5 += " / /__ ";
        level6 += "/_____|";
    }

    private static void drawSpace() {
        level1 += "       ";
        level2 += "       ";
        level3 += "       ";
        level4 += "       ";
        level5 += "       ";
        level6 += "       ";
    }

    private static void drawPeriod() {
        level1 += "   ";
        level2 += "   ";
        level3 += "   ";
        level4 += "   ";
        level5 += " _ ";
        level6 += "(_)";
    }

    private static void drawExclamation() {
        level1 += " _ ";
        level2 += "| |";
        level3 += "| |";
        level4 += "| |";
        level5 += "|_|";
        level6 += "(_)";
    }

    private static void drawQuestion() {
        level1 += " ___  ";
        level2 += "|__ \\ ";
        level3 += "   ) |";
        level4 += "  / / ";
        level5 += " |_|  ";
        level6 += " (_)  ";
    }

    private static void drawComma() {
        level1 += "   ";
        level2 += "   ";
        level3 += "   ";
        level4 += " _ ";
        level5 += "( )";
        level6 += "|/ ";
    }

    private static void drawApostrophe() {
        level1 += " _ ";
        level2 += "( )";
        level3 += "|/ ";
        level4 += "   ";
        level5 += "   ";
        level6 += "   ";
    }

    private static void drawEquals() {
        level1 += "        ";
        level2 += " ______ ";
        level3 += "|______|";
        level4 += " ______ ";
        level5 += "|______|";
        level6 += "        ";
    }

    private static void drawLess() {
        level1 += "   __";
        level2 += "  / /";
        level3 += " / / ";
        level4 += "< <  ";
        level5 += " \\ \\ ";
        level6 += "  \\_\\";
    }

    private static void drawMore() {
        level1 += "__   ";
        level2 += "\\ \\  ";
        level3 += " \\ \\ ";
        level4 += "  > >";
        level5 += " / / ";
        level6 += "/_/  ";
    }

    private static void drawForward() {
        level1 += "     __";
        level2 += "    / /";
        level3 += "   / / ";
        level4 += "  / /  ";
        level5 += " / /   ";
        level6 += "/_/    ";
    }

    private static void drawBack() {
        level1 += "__     ";
        level2 += "\\ \\    ";
        level3 += " \\ \\   ";
        level4 += "  \\ \\  ";
        level5 += "   \\ \\ ";
        level6 += "    \\_\\";
    }

    private static void drawColon() {
        level1 += " _ ";
        level2 += "(_)";
        level3 += "   ";
        level4 += " _ ";
        level5 += "(_)";
        level6 += "   ";
    }

    private static void drawSemicolon() {
        level1 += " _ ";
        level2 += "(_)";
        level3 += "   ";
        level4 += " _ ";
        level5 += "( )";
        level6 += "|/ ";
    }

    private static void drawOpenParenthesis() {
        level1 += "  __";
        level2 += " / /";
        level3 += "| | ";
        level4 += "| | ";
        level5 += "| | ";
        level6 += " \\_\\";
    }

    private static void drawCloseParenthesis() {
        level1 += "__  ";
        level2 += "\\ \\ ";
        level3 += " | |";
        level4 += " | |";
        level5 += " | |";
        level6 += "/_/ ";
    }

    private static void drawQuotes() {
        level1 += " _ _ ";
        level2 += "( | )";
        level3 += " V V ";
        level4 += "     ";
        level5 += "     ";
        level6 += "     ";
    }

    private static void drawAt() {
        level1 += "   ____  ";
        level2 += "  / __ \\ ";
        level3 += " / / _` |";
        level4 += "| | (_| |";
        level5 += " \\ \\__,_|";
        level6 += "  \\____/ ";
    }

    private static void drawNumber() {
        level1 += "   _  _   ";
        level2 += " _| || |_ ";
        level3 += "|_  __  _|";
        level4 += " _| || |_ ";
        level5 += "|_  __  _|";
        level6 += "  |_||_|  ";
    }

    private static void drawDollar() {
        level1 += "  _  ";
        level2 += " | | ";
        level3 += "/ __)";
        level4 += "\\__ \\";
        level5 += "(   /";
        level6 += " |_| ";
    }

    private static void drawPercent() {
        level1 += " _   __";
        level2 += "(_) / /";
        level3 += "   / / ";
        level4 += "  / /  ";
        level5 += " / / _ ";
        level6 += "/_/ (_)";
    }

    private static void drawUp() {
        level1 += "  /  \\  ";
        level2 += " / /\\ \\ ";
        level3 += "/_/  \\_\\";
        level4 += "        ";
        level5 += "        ";
        level6 += "        ";
    }

    private static void drawAnd() {
        level1 += "        ";
        level2 += "  ___   ";
        level3 += " ( _ )  ";
        level4 += " / _ \\/\\";
        level5 += "| (_>  <";
        level6 += " \\___/\\/";
    }

    private static void drawAsterisk() {
        level1 += "    _    ";
        level2 += " /\\| |/\\ ";
        level3 += " \\     / ";
        level4 += "|_     _|";
        level5 += " /     \\ ";
        level6 += " \\/|_|\\/ ";
    }
}

class CommieAI {

    public void listen(String secretlistener) {
        if (secretlistener.length() < 5)
            secretlistener = secretlistener + "          ";
        if (secretlistener.contains("gay") || secretlistener.contains("gey"))
            System.out.println("no u");
        if (secretlistener.contains("niggr") || secretlistener.contains("nigger") || secretlistener.contains("nigga") || secretlistener.contains("bibba") || secretlistener.contains("nibber") || secretlistener.substring(0, 4).contains("nigg") || secretlistener.substring(0, 4).contains("nibb") || secretlistener.substring(0, 4).contains("bibb") || secretlistener.substring(0, 4).contains("bigg") || secretlistener.substring(0, 4).contains("nig") || secretlistener.substring(0, 4).contains("nib"))
            System.out.println("your family tree lgbt");
        if (secretlistener.contains("minecraft") || secretlistener.substring(0, 5).contains("minec"))
            System.out.println("stfu mom im playing minecraft");
        if (secretlistener.contains("roblox") || secretlistener.substring(0, 4).contains("robl"))
            System.out.println("i want robux for my birthday");
        if (secretlistener.contains("ireland") || secretlistener.substring(0, 3).contains("ire"))
            System.out.println("26 + 6 = 1");
        if (secretlistener.contains("beer") || secretlistener.contains("alcohol") || secretlistener.substring(0, 4).contains("alco"))
            System.out.println("irish handcuffs");
        if (secretlistener.substring(0, 4).contains("gam") || secretlistener.substring(0, 4).contains("game"))
            System.out.println("in my basement *wink*");
        if (secretlistener.substring(0, 4).contains("mom") || secretlistener.substring(0, 4).contains("mum") || secretlistener.substring(0, 4).contains("m0m"))
            System.out.println("ur dad lesbian");
        if (secretlistener.substring(0, 4).contains("lol") || secretlistener.substring(0, 4).contains("lul") || secretlistener.substring(0, 4).contains("l0l"))
            System.out.println("thats pretty gay");
    }
}
