import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.lang.StackOverflowError;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.*;
public class ToolCollection{
    public static void main(String[] args)
    {
        try{
            Tools tool = new Tools();
            Recovery recovery = new Recovery();
            recovery.runAndRecover(tool);
        }
        catch(InputMismatchException e)
        {
            System.out.println("InputMismatchException in main()");
        }
        catch(NoSuchElementException e)
        {
            System.out.println("NoSuchElementException in main()");
        }
        catch(Exception e)
        {
            System.out.println("Exception in main()");
        }
        catch(StackOverflowError e)
        {
            System.out.println("StackOverflowError in main()");
        }
        catch(Error e)
        {
            System.out.println("Error in main()");
        }
        main(args);
    }
}
class Tools implements Runnable, KeyListener{
    private static String salt;
    private static String salt2;
    private static String hash1;
    private static String finalHash;
    public static String[] stopwords = {"exit","Exit","break","Break","stop","Stop","end","End","terminate","Terminate"};
    public static String[] helpwords = {"help", "Help", "functions", "Functions"};
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()`~-_=+[{]};:',./<>?\"|";
    private static boolean gpugrlc = false;
    private static boolean cpugrlc = false;
    private static CommieAI ai = new CommieAI();
    public static boolean stop = false;
    public Tools()
    {
    }
    @Override
    public void run()
    {
        try {
            ASCIIDL.ASCII("YTool v0.1", false);
            Execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void hashInit()
    {
        salt2 = salt2+sha256(randomAlphaNumeric(64));
        salt2 = sha256(salt2);
        salt2 = salt2.substring(0, 15);
    }
    public static void Execute() throws Exception
    {
        int x = -1; int y = -1;
        while(true)
        {
            x = -1;
            while(x == -1 && y == -1)
            {
                Scanner sc = new Scanner(System.in);
                String action = sc.nextLine();
                action = action.toLowerCase();
                String[] aiwords = action.split(" ");
                for(String keyword:aiwords)
                {
                    ai.listen(keyword);
                }
                if((action.equals("clear")||action.equals("Clear")))
                {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); ASCIIDL.ASCII("YTool v0.1", false);
                }
                else if(action.equals("getrandomhash"))
                {
                    if(!(action.equals(null)))
                    {
                        salt2 = salt2 + action;
                    }
                    hashInit();
                    System.out.println(getHash());
                }
                else if(action.equals("mineblock"))
                    mineBlock();
                else if(action.equals("secretfunctions"))
                {
                    gpugrlc=true;
                    cpugrlc=true;
                }
                else if(action.equals("getcredits"))
                {
                    System.out.println("Program by Yicheng Quan, MethodsDL and subclasses by Damian Lall");
                }
                else if(action.equals("base2dec"))
                {
                    String base2dec = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.base2dec(base2dec, base));
                }
                else if(action.equals("base2hex"))
                {
                    String base2hex = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.base2hex(base2hex, base));
                }
                else if(action.equals("base2bin"))
                {
                    String base2bin = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.base2bin(base2bin, base));
                }
                else if(action.equals("base2base"))
                {
                    String base2base = sc.nextLine();
                    int base = sc.nextInt();
                    int desiredbase = sc.nextInt();
                    System.out.println(literalHash.base2base(base2base, base, desiredbase));
                }
                else if(action.equals("dec2base"))
                {
                    BigInteger dec2base = new BigInteger(sc.nextLine());
                    int base = sc.nextInt();
                    System.out.println(literalHash.dec2base(dec2base, base));
                }
                else if(action.equals("hex2base"))
                {
                    String hex2base = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.hex2base(hex2base, base));
                }
                else if(action.equals("bin2base"))
                {
                    String bin2base = sc.nextLine();
                    int base = sc.nextInt();
                    System.out.println(literalHash.bin2base(bin2base, base));
                }
                else if(action.equals("gpugrlc")&&gpugrlc==true)
                {
                    literalHash.gpugrlc();
                    gpugrlc=false;
                    if(gpugrlc==false)
                        System.out.println("this.state=0");
                    else
                        System.out.println("this.state=1");
                    if(cpugrlc==false)
                        System.out.println("cpu.state=0");
                    else
                        System.out.println("cpu.state=1");

                }
                else if(action.equals("bibbafy"))
                {
                    String bibbafy = sc.nextLine();
                    System.out.println(literalHash.bibbafy(bibbafy));
                }
                else if(action.equals("discordbibbafy"))
                {
                    String discordbibbafy = sc.nextLine();
                    System.out.println(literalHash.discordbibbafy(discordbibbafy));
                }
                else if(action.equals("cpugrlc")&&cpugrlc==true)
                {
                    literalHash.cpugrlc();
                    cpugrlc=false;
                    if(gpugrlc==false)
                        System.out.println("gpu.state=0");
                    else
                        System.out.println("gpu.state=1");
                    if(cpugrlc==false)
                        System.out.println("this.state=0");
                    else
                        System.out.println("this.state=1");
                }
                else if(action.equals("getstate"))
                {
                    if(gpugrlc==false)
                        System.out.println("gpu.state=0");
                    else
                        System.out.println("gpu.state=1");
                    if(cpugrlc==false)
                        System.out.println("cpu.state=0");
                    else
                        System.out.println("cpu.state=1");
                }
                else if(action.equals("setfalse"))
                {
                    cpugrlc=false;gpugrlc=false;
                }
                else if(action.equals("getepoch"))
                {
                    System.out.println(System.currentTimeMillis());
                }
                else if(action.equals("leetify"))
                {
                    String leetify = sc.nextLine();
                    System.out.println(literalHash.leetify(leetify));
                }
                else if(action.equals("getrandomstring"))
                {
                    int size = sc.nextInt();
                    System.out.println(randomAlphaNumeric(size));
                }
                else if(action.equals("throwexception"))
                {
                    throw new TestException("This was thrown at request for debug purposes, the auto recovery system should kick in.\nYou should be able to still type commands if the program did not encounter any unknown errors.\nTL:DR DON'T FREAK OUT THE PROGRAM RECOVERED, IT IS STILL RUNNING.");
                }
                else if(action.equals("dec2bin"))
                {
                    BigInteger dec2bin = new BigInteger(sc.nextLine());
                    System.out.println(literalHash.dec2bin(dec2bin));
                }
                else if(action.equals("hex2bin"))
                {
                    String hex2bin = sc.nextLine();
                    System.out.println(literalHash.hex2bin(hex2bin));
                }
                else if(action.equals("hex2dec"))
                {
                    String hex2dec = sc.nextLine();
                    System.out.println(literalHash.hex2dec(hex2dec));
                }
                else if(action.equals("directhash"))
                {
                    String literalhash = sc.nextLine();
                    System.out.println(literalHash.hash(literalhash));
                }
                else if(action.equals("dec2hex"))
                {
                    try {
                        BigInteger dec2hex = new BigInteger(sc.nextLine());
                        System.out.println(literalHash.dec2hex(dec2hex));
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Unrecognizable input");
                    }
                }
                else if(action.equals("bin2dec"))
                {
                    String bin2dec = sc.nextLine();
                    System.out.println(literalHash.bin2dec(bin2dec));
                }
                else if(action.equals("bin2hex"))
                {
                    String bin2hex = sc.nextLine();
                    System.out.println(literalHash.bin2hex(bin2hex));
                }
                if(Arrays.asList(stopwords).contains(action)||Arrays.asList(helpwords).contains(action)||action.equals("leetify")||action.equals("getrandomstring")||action.equals("mineblock")||action.equals("throwexception")||action.equals("getrandomhash")||action.equals("getepoch")||action.equals("dec2hex")||action.equals("bin2dec")||action.equals("bin2hex")||(action.equals("clear")||action.equals("hex2dec")||action.equals("Clear")||action.equals("dec2bin")||action.equals("hex2bin")||action.equals("directhash")||action.equals("secretfunctions")||action.equals("gpugrlc")||action.equals("cpugrlc")||action.equals("getstate")||action.equals("setfalse")||action.equals("bibbafy")||action.equals("discordbibbafy")||action.equals("base2dec")||action.equals("base2hex")||action.equals("base2bin")||action.equals("base2base")||action.equals("dec2base")||action.equals("hex2base")||action.equals("bin2base"))||action.equals("getcredits"))
                {
                    if(Arrays.asList(helpwords).contains(action))
                    {
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
                        System.out.println("leetify-converts words using traditional english characters into mainstream leet");
                        System.out.println("bibbafy-adds them hard Bs to your text");
                        System.out.println("discordbibbafy-optimized for discord :wink:");
                        System.out.println("getcredits-gets the contributors or sources for this program");
                        System.out.println("mineblock-kinda mines a block using hashes");
                    }
                }
                else
                    System.out.println("This command does not seem to exist, to see a list of commands, type 'help'.");
                if(Arrays.asList(stopwords).contains(action))
                {
                    System.out.println("Are you sure? [Y/N]");
                    char stop = sc.nextLine().charAt(0);
                    switch(stop)
                    {
                        case 'y': case 'Y': System.out.println("Closing Program..."); Thread.sleep(512); System.exit(0); break;
                        case 'n': case 'N': x = -1; break;
                        default:continue;
                    }
                }
            }
        }
    }
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    private static String getNewSalt(String oldSalt) {
        String randomSalt = sha256(""+randomAlphaNumeric(256));
        randomSalt = randomSalt.substring(0, 15);
        String newSalt = sha256(oldSalt+randomSalt);
        newSalt = newSalt.substring(0, 15);
        return newSalt;
    }
    private static String getHash() {
        salt = sha256(getBase());
        salt = salt.substring(0, 15);
        hash1 = sha256(salt2+salt+getBase()+salt2);
        salt = getNewSalt(salt);
        salt2 = getNewSalt(salt2);
        finalHash = sha256(salt2+salt+hash1+sha256(getBase()+salt+salt2));
        salt = getNewSalt(salt);
        salt2 = getNewSalt(salt2);
        finalHash = sha256(sha256(salt2+finalHash+salt+hash1+salt2));
        return finalHash;
    }
    public static String getBase()
    {
        return randomAlphaNumeric(64);
    }
    public static void mineBlock() throws InterruptedException, IOException
    {
        DecimalFormat df = new DecimalFormat("#.###");
        Scanner sc = new Scanner(System.in);
        int diff = sc.nextInt();
        int mRange = diff + (int) (Math.random() * (64 - diff));
        int ct = 0;
        long prevTime;
        if(diff>64)
            diff=64;
        hashInit();
        String target = getHash().substring(mRange-diff, mRange);
        String current = getHash();
        long startTime = System.currentTimeMillis();
        long callTime = startTime;
        long timeDiff = 0;
        long hashesps = 0;
        long kh, mh, gh, th, ph, eh;
        kh=1000;
        mh=kh*1000;
        gh=mh*1000;
        th=gh*1000;
        ph=th*1000;
        eh=ph*1000;
        prevTime = timeDiff; 
        boolean refreshScreen=false;
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        while(!(current.contains(target))&&!stop)
        {        
            ct++;
          if((timeDiff-prevTime)>256)
          {
            hashesps = (ct/(timeDiff/1000));
            refreshScreen = true;
            System.out.print("Target: 0x"+target+" Result: "+current+" Diff: "+literalHash.dec2hex(new BigInteger(diff+"")));
          }  
            hashInit();
            current=getHash();
            timeDiff = (new Date()).getTime() - startTime;
            if ((timeDiff/1000)<1)
                timeDiff=1000;
          if((refreshScreen))
          {
           if(hashesps>kh&&!(hashesps>mh))
           {
             System.out.print(" KH/s: "+df.format(((double)ct/(double)(timeDiff/1000))/kh));
           }
           else if(hashesps>mh&&!(hashesps>gh))
           {
             System.out.print(" MH/s: "+df.format(((double)ct/(double)(timeDiff/1000))/mh));
           }
           else if(hashesps>gh&&(hashesps>th))
           {
             System.out.print(" GH/s: "+df.format(((double)ct/(double)(timeDiff/1000))/gh));
           }
           else if(hashesps>th&&!(hashesps>ph))
           {
             System.out.print(" TH/s: "+df.format(((double)ct/(double)(timeDiff/1000))/th));
           }
           else if(hashesps>ph&&!(hashesps>eh))
           {
             System.out.print(" PH/s: "+df.format(((double)ct/(double)(timeDiff/1000))/ph));
           }
           else if(hashesps>eh)
           {
             System.out.print(" EH/s: "+df.format(((double)ct/(double)(timeDiff/1000))/eh));
           }
           else
            System.out.print(" H/s: "+(ct/(timeDiff/1000)));
            System.out.print((char)13);
            refreshScreen = false;
          }
            if ((timeDiff/1000)/60>=2)
            {
                ct=0;
                startTime = System.currentTimeMillis();
            } 
        }
        if(!stop)
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("Valid Hash: "+ current+ " Target: 0x"+target+" Diff: "+literalHash.dec2hex(new BigInteger(diff+""))+" Time until solution in minutes: "+df.format((((double)System.currentTimeMillis()-(double)callTime)/(double)1000)/(double)60));
            //(.64*(Diff*0.1))*TimeSpent = CLM Owed
        }

    }
    public void keyPressed(KeyEvent e) {
        boolean s = false;
        boolean ctrl = false;
        if(e.getKeyCode() == KeyEvent.VK_S)
            s = true;
        else if(e.getKeyCode() == KeyEvent.VK_CONTROL)
            ctrl = true;
        if(s&&ctrl)
            stop = true;

    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {stop = false;}
}
class literalHash{
    final public static String list = "GHIJKLMNOPQRSTUVWXYZghijklmnopqrstuvwxyz!@#$%^&*()`~-_=+[{]};:',./<>?\"|";
    final public static String binchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()`~-_=+[{]};:',./<>?\"|";
    public static char[] chararray = list.toCharArray();
    public static List<Character> nonAcceptableHex = new ArrayList<Character>();
    public static List<Character> nonAcceptableBin = new ArrayList<Character>();
    public static String bin = "";
    public static String hash(String base)
    {
        return Tools.sha256(base);
    }
    public static BigInteger hex2dec(String hex)
    {
        for(char i:chararray)
        {
            nonAcceptableHex.add(i);
        }
        char[] contents = hex.toCharArray();
        for(int i = 0; i<contents.length; i++)
        {
            if(nonAcceptableHex.contains(contents[i]))
            {
                System.out.println("non hex characters detected");
                return new BigInteger("-1");
            }
        }
        BigInteger bi = new BigInteger(hex, 16);
        return bi;
    }
    public static String dec2hex(BigInteger base)
    {
        try {
            return base.toString(16);
        }
        catch(Exception e)
        {
            return "Unrecognizable input";
        }

    }
    public static String dec2bin(BigInteger dec)
    {
        try {
            if(dec.compareTo(BigInteger.ONE)==-1||dec.compareTo(BigInteger.ONE)==0)
                bin = bin+dec.mod(BigInteger.valueOf(2));
            else
            {
                bin = bin+dec2bin(dec.divide(BigInteger.valueOf(2)));
                bin = bin + dec.mod(BigInteger.valueOf(2));
            }
            String temp = bin;
            bin = "";
            return temp;
        }
        catch(Exception e)
        {
            return "Unrecognizable input";
        }
    }
    public static String bin2dec(String bin)
    {
        String res = "";
        char[] binary = bin.toCharArray();
        BigInteger decimal = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(2);
        for(int j = binary.length-1; j >= 0; j--)
        {
            if(binary[j]=='1')
                decimal = decimal.add((base.pow((binary.length-j)-1)));
            else if(binary[j]!='1'&&binary[j]!='0')
            {
                decimal = new BigInteger("-1");
                res = "this result is wrong, as you used a non-binary number";
                return res;
            }
        }
        return res+decimal;
    }
    public static String bin2hex(String binary)
    {
        try {
            String res = "";
            BigInteger decimal = new BigInteger(bin2dec(binary));
            return dec2hex(decimal);
        }
        catch(Exception e)
        {
            return "Unrecognizable input";
        }

    }
    public static String hex2bin(String hex)
    {
        for(char i:chararray)
        {
            nonAcceptableHex.add(i);
        }
        char[] contents = hex.toCharArray();
        for(int i = 0; i<contents.length; i++)
        {
            if(nonAcceptableHex.contains(contents[i]))
            {
                return "non hex characters detected";
            }
        }
        BigInteger dec = hex2dec(hex);
        String binary = dec2bin(dec);
        String standin = binary;
        binary = "";
        return standin;
    }
    public static BigInteger base2dec(String number, int base)
    {
        BigInteger bi = new BigInteger(number, base);
        return bi;
    }
    public static String base2hex(String number, int base)
    {
        BigInteger bi = base2dec(number, base);
        return dec2hex(bi);
    }
    public static String base2bin(String number, int base)
    {
        BigInteger bi = new BigInteger(number, base);
        return dec2bin(bi);
    }
    public static String base2base(String number, int base, int desiredbase)
    {
        BigInteger bi = new BigInteger(number, base);
        return bi.toString(desiredbase);
    }
    public static String dec2base(BigInteger bi, int desiredbase)
    {
        return bi.toString(desiredbase);
    }
    public static String hex2base(String hex, int desiredbase)
    {
        BigInteger bi = new BigInteger(hex, 16);
        return bi.toString(desiredbase);
    }
    public static String bin2base(String bin, int desiredbase)
    {
        BigInteger bi = new BigInteger(bin2dec(bin));
        return bi.toString(desiredbase);
    }
    public static String leetify(String inputs)
    {
        String edit = inputs;
        char [] editchar = edit.toCharArray();
        for(int i = 0; i < editchar.length; i++)
        {
            if(editchar[i]=='a'||editchar[i]=='A')
            {
                editchar[i] = '4';
            }
            else if(editchar[i]=='b'||editchar[i]=='B')
            {
                editchar[i] = '8';
            }
            else if(editchar[i]=='i'||editchar[i]=='I')
            {
                editchar[i] = '1';
            }
            else if(editchar[i]=='q'||editchar[i]=='Q')
            {
                editchar[i] = '9';
            }
            else if(editchar[i]=='s'||editchar[i]=='S')
            {
                editchar[i] = '5';
            }
            else if(editchar[i]=='z'||editchar[i]=='Z')
            {
                editchar[i] = '2';
            }
            else if(editchar[i]=='e'||editchar[i]=='E')
            {
                editchar[i] = '3';
            }
            else if(editchar[i]=='g'||editchar[i]=='G')
            {
                editchar[i] = '6';
            }
            else if(editchar[i]=='o'||editchar[i]=='O')
            {
                editchar[i] = '0';
            }
        }
        edit = new String(editchar);
        return edit;
    }
    public static String bibbafy(String inputs)
    {
        String edit = inputs;
        char [] editchar = edit.toCharArray();
        for(int i = 0; i < editchar.length; i++)
        {
            if(editchar[i]=='b')
            {
                editchar[i] = 'B';
            }
        }
        edit = new String(editchar);
        return edit;
    }
    public static String discordbibbafy(String inputs)
    {
        inputs = inputs.replaceAll("B", "b");
        return inputs = inputs.replaceAll("b", ":b:");
    }
    public static void gpugrlc()
    {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", "Garlic.bat");
            File dir = new File("C:\\Users\\yquan\\Desktop\\ccminer");
            pb.directory(dir);
            Process p = pb.start();
        }
        catch(IOException e)
        {
            System.out.println("Program failed to start");
        }
    }
    public static void cpugrlc()
    {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", "Garlic.bat");
            File dir = new File("C:\\Users\\yquan\\Desktop\\cpuminer-opt-3.8.3.1-windows");
            pb.directory(dir);
            Process p = pb.start();
        }
        catch(IOException e)
        {
            System.out.println("Program failed to start");
        }
    }

}
class Recovery {

    final Object lock = new Object();

    void runAndRecover(final Runnable runnable) { // Will pass an instance of Tools
        while (true) {
            // Synchronising to make sure that only one instance
            // of runnable input is running at the time.
            synchronized(lock) {
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
class TestException extends Exception
{
    public TestException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}
class MethodsDL
{
    static final String author = "Damian Lall, edited by Yicheng Quan";
    static final double version = 1.8D;

    public static void main(String[] args)
    {
        System.out.println();
    }

    public static void Info()
    {
        System.out.println("Author: Damian Lall, Contributor: Yicheng Quan");
        Delay(250);
        System.out.println("Version: 1.8");
        Delay(250);
        System.out.println("Subclasses: deprecated(MathDL), ASCIIDL");
    }

    public static void Commands()
    {
        System.out.println("Info(): Provides general information regarding the MethodsDL class.");
        System.out.println("Commands(): Provides a list of MethodsDL commands and their uses.");
        System.out.println("Delay(int delayTime): Delays the program for the specified number of milliseconds.");
        System.out.println("Random(int min, int max): Generates a random number between the min and max.");
    }

    public static void Delay(int delayTime)
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(delayTime);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(MethodsDL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Load(String load)
    {
        Load(load, 200);
    }

    public static void Load(String load, int speed)
    {
        System.out.print(load);
        Delay(speed);
        System.out.print(".");
        Delay(speed);
        System.out.print(".");
        Delay(speed);
        System.out.println(".");
        Delay(speed);
    }

    public static void SlowReveal(String reveal)
    {
        SlowReveal(reveal, 20);
    }

    public static void SlowReveal(String reveal, int speed)
    {
        char[] split = reveal.toCharArray();
        for (char r : split)
        {
            System.out.print(r);
            Delay(speed);
        }
        System.out.println();
    }

    public static int Random(int min, int max)
    {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public static int ChooseRandom(int[] arr)
    {
        return arr[Random(0, arr.length - 1)];
    }

    public static double ChooseRandom(double[] arr)
    {
        return arr[Random(0, arr.length - 1)];
    }

    public static String ChooseRandom(String[] arr)
    {
        return arr[Random(0, arr.length - 1)];
    }
}
class ASCIIDL extends MethodsDL
{
    public static void Info()
    {
        System.out.println("Author: Damian Lall");
        Delay(250);
        System.out.println("Version: 1.7");
        Delay(250);
        System.out.println("Main Class: MethodsDL");
        Delay(250);
        System.out.println("Other Classes: MathDL");
    }

    public static void Commands()
    {
        System.out.println("Info(): Provides general information regarding the ASCIIDL class.");
        System.out.println("Commands(): Provides a list of ASCIIDL commands and their uses.");
        System.out.println("ASCII(String text, boolean slow): Displays the inputted text as large ASCII-based characters.");
    }

    private static String level1 = "";
    private static String level2 = "";
    private static String level3 = "";
    private static String level4 = "";
    private static String level5 = "";
    private static String level6 = "";

    public static void ASCII(String text, boolean slow)
    {
        char[] textArray = text.toUpperCase().toCharArray();

        level1 = "";
        level2 = "";
        level3 = "";
        level4 = "";
        level5 = "";
        level6 = "";
        for (int i = 0; i < textArray.length; i++) {
            switch (textArray[i])
            {
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
                    System.out.println("Error - Invalid Charachter");
            }
        }
        if (level1.length() > 79)
        {
            level1 = level1.substring(0, 79);
            level2 = level2.substring(0, 79);
            level3 = level3.substring(0, 79);
            level4 = level4.substring(0, 79);
            level5 = level5.substring(0, 79);
            level6 = level6.substring(0, 79);
        }
        if (slow)
        {
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
        }
        else
        {
            System.out.println(level1);
            System.out.println(level2);
            System.out.println(level3);
            System.out.println(level4);
            System.out.println(level5);
            System.out.println(level6);
        }
    }
    private static void drawZero()
    {
        level1 += "  ____  ";
        level2 += " / __ \\ ";
        level3 += "| | /| |";
        level4 += "| |/ | |";
        level5 += "| |__| |";
        level6 += " \\____/ ";
    }
    private static void drawOne()
    {
        level1 += " __ ";
        level2 += "/_ |";
        level3 += " | |";
        level4 += " | |";
        level5 += " | |";
        level6 += " |_|";
    }

    private static void drawTwo()
    {
        level1 += " ___  ";
        level2 += "|__ \\ ";
        level3 += "   ) |";
        level4 += "  / / ";
        level5 += " / /_ ";
        level6 += "|____|";
    }

    private static void drawThree()
    {
        level1 += " ____  ";
        level2 += "|___ \\ ";
        level3 += "  __) |";
        level4 += " |__ < ";
        level5 += " ___) |";
        level6 += "|____/ ";
    }

    private static void drawFour()
    {
        level1 += " _  _   ";
        level2 += "| || |  ";
        level3 += "| || |_ ";
        level4 += "|__   _|";
        level5 += "   | |  ";
        level6 += "   |_|  ";
    }

    private static void drawFive()
    {
        level1 += " _____ ";
        level2 += "| ____|";
        level3 += "| |__  ";
        level4 += "|___ \\ ";
        level5 += " ___) |";
        level6 += "|____/ ";
    }

    private static void drawSix()
    {
        level1 += "   __  ";
        level2 += "  / /  ";
        level3 += " / /_  ";
        level4 += "| '_ \\ ";
        level5 += "| (_) |";
        level6 += " \\___/ ";
    }

    private static void drawSeven()
    {
        level1 += " ______ ";
        level2 += "|____  |";
        level3 += "    / / ";
        level4 += "   / /  ";
        level5 += "  / /   ";
        level6 += " /_/    ";
    }

    private static void drawEight()
    {
        level1 += "  ___  ";
        level2 += " / _ \\ ";
        level3 += "| (_) |";
        level4 += " > _ < ";
        level5 += "| (_) |";
        level6 += " \\___/ ";
    }

    private static void drawNine()
    {
        level1 += "  ___  ";
        level2 += " / _ \\ ";
        level3 += "| (_) |";
        level4 += " \\__, |";
        level5 += "   / / ";
        level6 += "  /_/  ";
    }

    private static void drawA()
    {
        level1 += "          ";
        level2 += "    /\\    ";
        level3 += "   /  \\   ";
        level4 += "  / /\\ \\  ";
        level5 += " / ____ \\ ";
        level6 += "/_/    \\_\\";
    }

    private static void drawB()
    {
        level1 += " ____  ";
        level2 += "|  _ \\ ";
        level3 += "| |_) |";
        level4 += "|  _ < ";
        level5 += "| |_) |";
        level6 += "|____/ ";
    }

    private static void drawC()
    {
        level1 += "  _____ ";
        level2 += " / ____|";
        level3 += "| |     ";
        level4 += "| |     ";
        level5 += "| |____ ";
        level6 += " \\_____|";
    }

    private static void drawD()
    {
        level1 += " _____  ";
        level2 += "|  __ \\ ";
        level3 += "| |  | |";
        level4 += "| |  | |";
        level5 += "| |__| |";
        level6 += "|_____/ ";
    }

    private static void drawE()
    {
        level1 += " ______ ";
        level2 += "|  ____|";
        level3 += "| |__   ";
        level4 += "|  __|  ";
        level5 += "| |____ ";
        level6 += "|______|";
    }

    private static void drawF()
    {
        level1 += " ______ ";
        level2 += "|  ____|";
        level3 += "| |__   ";
        level4 += "|  __|  ";
        level5 += "| |     ";
        level6 += "|_|     ";
    }

    private static void drawG()
    {
        level1 += "  _____ ";
        level2 += " / ____|";
        level3 += "| |  __ ";
        level4 += "| | |_ |";
        level5 += "| |__| |";
        level6 += " \\_____|";
    }

    private static void drawH()
    {
        level1 += " _    _ ";
        level2 += "| |  | |";
        level3 += "| |__| |";
        level4 += "|  __  |";
        level5 += "| |  | |";
        level6 += "|_|  |_|";
    }

    private static void drawI()
    {
        level1 += " _____ ";
        level2 += "|_   _|";
        level3 += "  | |  ";
        level4 += "  | |  ";
        level5 += " _| |_ ";
        level6 += "|_____|";
    }

    private static void drawJ()
    {
        level1 += "      _ ";
        level2 += "     | |";
        level3 += "     | |";
        level4 += " _   | |";
        level5 += "| |__| |";
        level6 += " \\____/ ";
    }

    private static void drawK()
    {
        level1 += " _  __";
        level2 += "| |/ /";
        level3 += "| ' / ";
        level4 += "|  <  ";
        level5 += "| . \\ ";
        level6 += "|_|\\_\\";
    }

    private static void drawL()
    {
        level1 += " _      ";
        level2 += "| |     ";
        level3 += "| |     ";
        level4 += "| |     ";
        level5 += "| |____ ";
        level6 += "|______|";
    }

    private static void drawM()
    {
        level1 += " __  __ ";
        level2 += "|  \\/  |";
        level3 += "| \\  / |";
        level4 += "| |\\/| |";
        level5 += "| |  | |";
        level6 += "|_|  |_|";
    }

    private static void drawN()
    {
        level1 += " _   _ ";
        level2 += "| \\ | |";
        level3 += "|  \\| |";
        level4 += "| . ` |";
        level5 += "| |\\  |";
        level6 += "|_| \\_|";
    }

    private static void drawO()
    {
        level1 += "  ____  ";
        level2 += " / __ \\ ";
        level3 += "| |  | |";
        level4 += "| |  | |";
        level5 += "| |__| |";
        level6 += " \\____/ ";
    }

    private static void drawP()
    {
        level1 += " _____  ";
        level2 += "|  __ \\ ";
        level3 += "| |__) |";
        level4 += "|  ___/ ";
        level5 += "| |     ";
        level6 += "|_|     ";
    }

    private static void drawQ()
    {
        level1 += "  ____  ";
        level2 += " / __ \\ ";
        level3 += "| |  | |";
        level4 += "| |  | |";
        level5 += "| |__| |";
        level6 += " \\___\\_\\";
    }

    private static void drawR()
    {
        level1 += " _____  ";
        level2 += "|  __ \\ ";
        level3 += "| |__) |";
        level4 += "|  _  / ";
        level5 += "| | \\ \\ ";
        level6 += "|_|  \\_\\";
    }

    private static void drawS()
    {
        level1 += "  _____ ";
        level2 += " / ____|";
        level3 += "| (___  ";
        level4 += " \\___ \\ ";
        level5 += " ____) |";
        level6 += "|_____/ ";
    }

    private static void drawT()
    {
        level1 += " _______ ";
        level2 += "|__   __|";
        level3 += "   | |   ";
        level4 += "   | |   ";
        level5 += "   | |   ";
        level6 += "   |_|   ";
    }

    private static void drawU()
    {
        level1 += " _    _ ";
        level2 += "| |  | |";
        level3 += "| |  | |";
        level4 += "| |  | |";
        level5 += "| |__| |";
        level6 += " \\____/ ";
    }

    private static void drawV()
    {
        level1 += "__      __";
        level2 += "\\ \\    / /";
        level3 += " \\ \\  / / ";
        level4 += "  \\ \\/ /  ";
        level5 += "   \\  /   ";
        level6 += "    \\/    ";
    }

    private static void drawW()
    {
        level1 += "__          __";
        level2 += "\\ \\        / /";
        level3 += " \\ \\  /\\  / / ";
        level4 += "  \\ \\/  \\/ /  ";
        level5 += "   \\  /\\  /   ";
        level6 += "    \\/  \\/    ";
    }

    private static void drawX()
    {
        level1 += "__   __";
        level2 += "\\ \\ / /";
        level3 += " \\ ' / ";
        level4 += "  > <  ";
        level5 += " / . \\ ";
        level6 += "/_/ \\_\\";
    }

    private static void drawY()
    {
        level1 += "__     __";
        level2 += "\\ \\   / /";
        level3 += " \\ \\_/ / ";
        level4 += "  \\   /  ";
        level5 += "   | |   ";
        level6 += "   |_|   ";
    }

    private static void drawZ()
    {
        level1 += " ______";
        level2 += "|___  /";
        level3 += "   / / ";
        level4 += "  / /  ";
        level5 += " / /__ ";
        level6 += "/_____|";
    }

    private static void drawSpace()
    {
        level1 += "       ";
        level2 += "       ";
        level3 += "       ";
        level4 += "       ";
        level5 += "       ";
        level6 += "       ";
    }

    private static void drawPeriod()
    {
        level1 += "   ";
        level2 += "   ";
        level3 += "   ";
        level4 += "   ";
        level5 += " _ ";
        level6 += "(_)";
    }

    private static void drawExclamation()
    {
        level1 += " _ ";
        level2 += "| |";
        level3 += "| |";
        level4 += "| |";
        level5 += "|_|";
        level6 += "(_)";
    }

    private static void drawQuestion()
    {
        level1 += " ___  ";
        level2 += "|__ \\ ";
        level3 += "   ) |";
        level4 += "  / / ";
        level5 += " |_|  ";
        level6 += " (_)  ";
    }

    private static void drawComma()
    {
        level1 += "   ";
        level2 += "   ";
        level3 += "   ";
        level4 += " _ ";
        level5 += "( )";
        level6 += "|/ ";
    }

    private static void drawApostrophe()
    {
        level1 += " _ ";
        level2 += "( )";
        level3 += "|/ ";
        level4 += "   ";
        level5 += "   ";
        level6 += "   ";
    }

    private static void drawEquals()
    {
        level1 += "        ";
        level2 += " ______ ";
        level3 += "|______|";
        level4 += " ______ ";
        level5 += "|______|";
        level6 += "        ";
    }

    private static void drawLess()
    {
        level1 += "   __";
        level2 += "  / /";
        level3 += " / / ";
        level4 += "< <  ";
        level5 += " \\ \\ ";
        level6 += "  \\_\\";
    }

    private static void drawMore()
    {
        level1 += "__   ";
        level2 += "\\ \\  ";
        level3 += " \\ \\ ";
        level4 += "  > >";
        level5 += " / / ";
        level6 += "/_/  ";
    }

    private static void drawForward()
    {
        level1 += "     __";
        level2 += "    / /";
        level3 += "   / / ";
        level4 += "  / /  ";
        level5 += " / /   ";
        level6 += "/_/    ";
    }

    private static void drawBack()
    {
        level1 += "__     ";
        level2 += "\\ \\    ";
        level3 += " \\ \\   ";
        level4 += "  \\ \\  ";
        level5 += "   \\ \\ ";
        level6 += "    \\_\\";
    }

    private static void drawColon()
    {
        level1 += " _ ";
        level2 += "(_)";
        level3 += "   ";
        level4 += " _ ";
        level5 += "(_)";
        level6 += "   ";
    }

    private static void drawSemicolon()
    {
        level1 += " _ ";
        level2 += "(_)";
        level3 += "   ";
        level4 += " _ ";
        level5 += "( )";
        level6 += "|/ ";
    }

    private static void drawOpenParenthesis()
    {
        level1 += "  __";
        level2 += " / /";
        level3 += "| | ";
        level4 += "| | ";
        level5 += "| | ";
        level6 += " \\_\\";
    }

    private static void drawCloseParenthesis()
    {
        level1 += "__  ";
        level2 += "\\ \\ ";
        level3 += " | |";
        level4 += " | |";
        level5 += " | |";
        level6 += "/_/ ";
    }

    private static void drawQuotes()
    {
        level1 += " _ _ ";
        level2 += "( | )";
        level3 += " V V ";
        level4 += "     ";
        level5 += "     ";
        level6 += "     ";
    }

    private static void drawAt()
    {
        level1 += "   ____  ";
        level2 += "  / __ \\ ";
        level3 += " / / _` |";
        level4 += "| | (_| |";
        level5 += " \\ \\__,_|";
        level6 += "  \\____/ ";
    }

    private static void drawNumber()
    {
        level1 += "   _  _   ";
        level2 += " _| || |_ ";
        level3 += "|_  __  _|";
        level4 += " _| || |_ ";
        level5 += "|_  __  _|";
        level6 += "  |_||_|  ";
    }

    private static void drawDollar()
    {
        level1 += "  _  ";
        level2 += " | | ";
        level3 += "/ __)";
        level4 += "\\__ \\";
        level5 += "(   /";
        level6 += " |_| ";
    }

    private static void drawPercent()
    {
        level1 += " _   __";
        level2 += "(_) / /";
        level3 += "   / / ";
        level4 += "  / /  ";
        level5 += " / / _ ";
        level6 += "/_/ (_)";
    }

    private static void drawUp()
    {
        level1 += "  /  \\  ";
        level2 += " / /\\ \\ ";
        level3 += "/_/  \\_\\";
        level4 += "        ";
        level5 += "        ";
        level6 += "        ";
    }

    private static void drawAnd()
    {
        level1 += "        ";
        level2 += "  ___   ";
        level3 += " ( _ )  ";
        level4 += " / _ \\/\\";
        level5 += "| (_>  <";
        level6 += " \\___/\\/";
    }

    private static void drawAsterisk()
    {
        level1 += "    _    ";
        level2 += " /\\| |/\\ ";
        level3 += " \\     / ";
        level4 += "|_     _|";
        level5 += " /     \\ ";
        level6 += " \\/|_|\\/ ";
    }
}
class CommieAI{
    static boolean first = true;
    static int ct = 0;
    public void listen(String secretlistener) {
        if(secretlistener.length()<5)
            secretlistener = secretlistener + "          ";
        if(secretlistener.contains("gay")||secretlistener.contains("gey"))
            System.out.println("no u");
        if(secretlistener.contains("niggr")||secretlistener.contains("nigger")||secretlistener.contains("nigga")||secretlistener.contains("bibba")||secretlistener.contains("nibber")||secretlistener.substring(0, 4).contains("nigg")||secretlistener.substring(0, 4).contains("nibb")||secretlistener.substring(0, 4).contains("bibb")||secretlistener.substring(0, 4).contains("bigg")||secretlistener.substring(0, 4).contains("nig")||secretlistener.substring(0, 4).contains("nib"))
            System.out.println("your family tree lgbt");
        if(secretlistener.contains("minecraft")||secretlistener.substring(0, 5).contains("minec"))
            System.out.println("stfu mom im playing minecraft");
        if(secretlistener.contains("roblox")||secretlistener.substring(0, 4).contains("robl"))
            System.out.println("i want robux for my birthday");
        if(secretlistener.contains("ireland")||secretlistener.substring(0, 3).contains("ire"))
            System.out.println("26 + 6 = 1");
        if(secretlistener.contains("beer")||secretlistener.contains("alcohol")||secretlistener.substring(0, 4).contains("alco"))
            System.out.println("irish handcuffs");
        if(secretlistener.substring(0, 4).contains("gam")||secretlistener.substring(0, 4).contains("game"))
            System.out.println("in my basement *wink*");
        if(secretlistener.substring(0, 4).contains("mom")||secretlistener.substring(0, 4).contains("mum")||secretlistener.substring(0, 4).contains("m0m"))
            System.out.println("ur dad lesbian");
        if(secretlistener.substring(0, 4).contains("lol")||secretlistener.substring(0, 4).contains("lul")||secretlistener.substring(0, 4).contains("l0l"))
            System.out.println("thats pretty gay");
    }
}