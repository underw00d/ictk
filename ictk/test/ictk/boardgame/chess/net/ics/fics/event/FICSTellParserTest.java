/*
 *  ICTK - Internet Chess ToolKit
 *  More information is available at http://ictk.sourceforge.net
 *  Copyright (C) 2002 J. Varsoke <jvarsoke@ghostmanonfirst.com>
 *  All rights reserved.
 *
 *  $Id$
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

package ictk.boardgame.chess.net.ics.fics.event;
import ictk.boardgame.chess.net.ics.event.*;
import ictk.boardgame.chess.net.ics.*;
import ictk.util.Log;

import java.util.regex.*;
import java.io.IOException;

import junit.framework.*;

public class FICSTellParserTest extends ParserTest {
   ICSTellEvent evt;

   public FICSTellParserTest () throws IOException {
      super("ictk.boardgame.chess.net.ics.fics.event");
   }

   public void setUp () {
      parser = FICSTellParser.getInstance();
      //debug = true;
   }

   public void tearDown () {
      evt = null;
      parser = null;
   }

   //////////////////////////////////////////////////////////////////////
   public void testMessage0 () {
      //debug = true;
      if (debug)
         Log.addMask(ICSEventParser.DEBUG);

      try {
	 parser.setDebug(true);
	 evt = (ICSTellEvent) parser.createICSEvent(mesg[0]);
	 assertTrue(evt != null);

	 //begin test
	 assertTrue(evt.getPlayer().equals("Handle"));
	 assertTrue(evt.getMessage().equals("Hey"));
	 assertFalse(evt.isFake());
	 assertTrue(evt.getEventType() == ICSEvent.TELL_EVENT);

	 assertFalse(evt.getMessage().equals("hey"));
	 //end test
      }
      finally {
         Log.removeMask(ICSEventParser.DEBUG);
	 debug = false;
      }
   }

   //////////////////////////////////////////////////////////////////////
   public void testMessage1 () {
      //debug = true;
      if (debug)
         Log.addMask(ICSEventParser.DEBUG);

      try {
	 parser.setDebug(true);
	 evt = (ICSTellEvent) parser.createICSEvent(mesg[1]);
	 assertTrue(evt != null);

	 //begin test
	 assertTrue(evt.getPlayer().equals("Handle"));
	 assertTrue(evt.getAccountType().is(ICSAccountType.COMPUTER));
	 assertTrue(evt.getMessage().equals("Hey"));
	 assertFalse(evt.isFake());
	 assertTrue(evt.getEventType() == ICSEvent.TELL_EVENT);

	 assertFalse(evt.getMessage().equals("hey"));
	 //end test
      }
      finally {
         Log.removeMask(ICSEventParser.DEBUG);
	 debug = false;
      }
   }

   //inherited///////////////////////////////////////////////////////////
   public void testParseAll () {
      //debug=true;
      if (debug) {
         Log.addMask(ICSEventParser.DEBUG);
         parser.setDebug(true);
      }
      try {
         super.testParseAll();
      }
      finally {
         Log.removeMask(ICSEventParser.DEBUG);
	 debug = false;
      }
   }

   //////////////////////////////////////////////////////////////////////
   public void testNative () {
      //debug=true;
      if (debug) {
         Log.addMask(ICSEventParser.DEBUG);
         parser.setDebug(true);
      }
      try {
         super.testNative();
      }
      finally {
         Log.removeMask(ICSEventParser.DEBUG);
	 debug = false;
      }
   }
}
