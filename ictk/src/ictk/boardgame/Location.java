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

package ictk.boardgame;

/** this is your standard square, position, or coordinate point on the
 *  playing board.
 */
public interface Location {

   /* getX ************************************************************/
   /** this is the internal horizontal coordinate for the position.
    */
   public int getX ();

   /* getY ************************************************************/
   /** this is the internal vertical coordinate for the position.
    */
   public int getY ();

   /* getPiece ********************************************************/
   /** returns the piece that is currently occupying this location.
    */
   public Piece getPiece ();

   public void setX (int x);
   public void setY (int y);

   /* setPiece ********************************************************/
   /** sets the piece for this location.
    */
   public Piece setPiece (Piece p);
}
