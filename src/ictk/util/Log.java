/*
 *  ICTK - Internet Chess ToolKit
 *  More information is available at http://ictk.sourceforge.net
 *  Copyright (C) 2002 J. Varsoke <jvarsoke@ghostmanonfirst.com>
 *  All rights reserved.
 *
 *  $Id: Log.java,v 1.4 2003/08/19 21:32:08 jvarsoke Exp $
 *
 *  This file is part of ICTK.
 *
 *  ICTK is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  ICTK is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with ICTK; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package ictk.util;

import java.io.PrintStream;
import java.util.regex.Matcher;

/* Log ***********************************************************************/
/**
 * Log - a static class with useful global debugging routines.  This
 *         can be used to manage Debug level output and route output
 *	   to log files or what have you. <br>
 *
 */
public class Log {
      /** to remove all debugging from bytecode set to false */
   public static final boolean debug = true;
      /** where the debug and error logs go */
   public static PrintStream err = System.err;
      /** where the more normal logs go */
   public static PrintStream out = System.out;

   public static final int PROG_CRITICAL = 1,
                           PROG_ERROR    = 2,
                           PROG_WARNING  = 3,
			   USER_CRITICAL = 4,
                           USER_ERROR    = 5, 
                           USER_WARNING  = 6;

   public static long History          = 1L,
                      Board            = 2 * History,
		      Move             = 2 * Board,
                      MoveNotation     = 2 * Move,
		      BoardNotation    = 2 * MoveNotation,
		      GameReader       = 2 * BoardNotation,
		      GameWriter       = 2 * GameReader,
		      GameInfo         = 2 * GameWriter,
		      ICSEvent         = 2 * GameInfo,
		      ICSEventParser   = 2 * ICSEvent;

      /** do you want fully qualified (package included) class names? */
   public static boolean isFullyQualifiedClass = false;

      /** should a timestamp be preappended to logs? */
   //public static boolean timestamp = false;

      /** the bit-mask for what we want to report*/
   protected static long mask_level = 0L;

   static {
     getSystemProperties();
   }

   private Log () {}

   /* getSystemProperties ****************************************************/
   /** get the system properties to find out what we want to debug
    *  from the command line.  This is done through the command:
    *  <pre>
    *  java -DDebug.History=1
    *  </pre>
    *  Note: if debug is false this will have no effect.
    */
   protected static void getSystemProperties () {
      if (debug) {
         if (System.getProperty("Debug.History") != null)
	    addMask(History);
	 if (System.getProperty("Debug.Board") != null)
	    addMask(Board);
	 if (System.getProperty("Debug.Move") != null)
	    addMask(Move);
	 if (System.getProperty("Debug.MoveNotation") != null)
	    addMask(MoveNotation);
	 if (System.getProperty("Debug.BoardNotation") != null)
	    addMask(BoardNotation);
	 if (System.getProperty("Debug.GameWriter") != null)
	    addMask(GameWriter);
	 if (System.getProperty("Debug.GameReader") != null)
	    addMask(GameReader);
      }
   }


   /* getMask ************************************************************/
   /** gets the current mask.
    */
   public static long getMask () {
      return mask_level;
   }

   /* setMask ************************************************************/
   /** set the whole mast */
   public static void setMask (long mask) {
      mask_level = mask;
   }

   /* addMask ************************************************************/
   /** add a debug mask to turn the output on for a certain group
    *  of classes.
    */
   public static void addMask (long mask) {
      mask_level |= mask;
   }

   /* removeMask ********************************************************/
   /** remove a debug mast to turn the output off for a certian group
    *  of classes.
    */
   public static void removeMask (long mask) {
      mask_level &= ~mask;
   }

   /* isDebug ***********************************************************/
   /** are we debugging this mask?
    */
   public static boolean isDebug (long mask) {
      return (mask_level & mask) == mask;
   }

   /* debug **************************************************************/
   /** prints out debug information to the log if the mask is 
    *  found in the current mask setting.  The message is prefaced
    *  by the Class and method name of the caller, along with the 
    *  line number.
    */
   public static void debug (long mask, Object o) {
      if ((mask_level & mask) == mask) {
         
         err.println("[" + getCaller(2) + "] " + o);
      }
   }

   /* debug2 ***********************************************************/
   /** debug2 is the same as debug() but does not print the
    *  ClassName.method() header.  This is often used as a follow up
    *  to a debug() call, where the header info would be redundant.
    */
   public static void debug2 (long mask, Object o) {
      if ((mask_level & mask) == mask)
         err.println(o);
   }


   /* errorIf **********************************************************/
   /** this reports an error if the boolean condition is true
    */
   public static void errorIf (boolean t, int severity, String msg) {
      if (t == false)
         errorReport(severity, msg);
   }

   /* error ************************************************************/
   /** reports the error message to the error stream.
    */
   public static void error (int severity, String msg) {
      errorReport(severity, msg);
   }

   /* errorReport ******************************************************/
   /** internal so the getCaller() call will be the same level for
    *  error and errorIf
    */
   protected static void errorReport (int severity, String msg) {
      if (severity < USER_CRITICAL) 
         err.print("[" + getCaller(3) + "] ");
      switch (severity) {
         case PROG_CRITICAL:
	 case USER_CRITICAL:
	    err.print("CRITICAL: ");
	    break;
	 case PROG_ERROR:
	 case USER_ERROR:
	    err.print("ERROR: ");
	    break;
	 case PROG_WARNING:
	 case USER_WARNING:
	    err.print("WARNING: ");
	    break;
	 default:
	    err.print("UNKNOWN: ");
      }
      err.println(msg);
   }

   /* debug ************************************************************/
   /** this debug call is used specifically to dump a Regex Matcher 
    *  object.
    */
   public static void debug (long mask, String description, Matcher m) {
      if ((mask_level & mask) == mask) {
	 err.println("[" + getCaller(2) + "] " + description);
	 for(int i=0; i<=m.groupCount(); i++) {
	    out.println(i + ": " + m.group(i));
	 }
      }
   }

   /** debug ***********************************************************/
   /** @return true if the mask is set
    */
   public static boolean debug (long mask) {
      return ((mask_level & mask) == mask);
   }

   /* getCaller() **********************************************************/
   /** gets the calling Class and method off the execution stack.
    *  @param lvl - which item on the stack do you want to see?
    */
   protected static String getCaller (int lvl) {
      StackTraceElement[] stack = null;
      String caller = null;
      String tmp = null;

      stack = new Throwable().getStackTrace();

      if (stack.length >= lvl) {
         
	 tmp = stack[lvl].getClassName();
         if (isFullyQualifiedClass)
	    caller = tmp;
	 else
	    caller = tmp.substring(tmp.lastIndexOf(".")+1);

	 caller += "." + stack[lvl].getMethodName() + "():";

	 if (stack[lvl].getLineNumber() >= 0)
	    caller += "" + stack[lvl].getLineNumber();
	 else
	    caller += "?";
      }
      else 
	 caller = "???";
      return caller;
   }

}
