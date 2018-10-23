/*

   Derby - Class org.apache.derby.impl.jdbc.EmbedResultSet20

   Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package org.apache.derby.impl.jdbc;

import org.apache.derby.iapi.reference.JDBC20Translation;
import org.apache.derby.iapi.reference.SQLState;

import org.apache.derby.iapi.sql.ResultSet;

import org.apache.derby.iapi.sql.execute.ExecCursorTableReference;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
                                         
import org.apache.derby.iapi.types.DataValueDescriptor;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Types;

/* ---- New jdbc 2.0 types ----- */
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;

/**
 * This class extends the EmbedResultSet class in order to support new
 * methods and classes that come with JDBC 2.0.
     <P><B>Supports</B>
   <UL>
   <LI> JDBC 2.0/2.1
   <LI> JDBC 3.0
   </UL>
 *      @see org.apache.derby.impl.jdbc.EmbedResultSet
 *
 *      @author francois
 */

public class EmbedResultSet20
        extends org.apache.derby.impl.jdbc.EmbedResultSet {

        //////////////////////////////////////////////////////////////
        //
        // CONSTRUCTORS
        //
        //////////////////////////////////////////////////////////////

        /**
         * This class provides the glue between the Derby
         * resultset and the JDBC resultset, mapping calls-to-calls.
         */
        public EmbedResultSet20(org.apache.derby.impl.jdbc.EmbedConnection conn, 
                                                         ResultSet resultsToWrap,  
                                                         boolean forMetaData,
                                                         org.apache.derby.impl.jdbc.EmbedStatement stmt,
                                                         boolean isAtomic)  
        throws SQLException {
                super(conn, resultsToWrap, forMetaData, stmt, isAtomic);
        }


		/*
		** Methods using java.math.BigDecimal, not supported in JSR169
		*/
		/**
		 * Get the value of a column in the current row as a java.lang.BigDecimal object.
		 *
		 * @param columnIndex the first column is 1, the second is 2, ...
		 * @param scale the number of digits to the right of the decimal
		 * @return the column value; if the value is SQL NULL, the result is null
		 * @exception SQLException thrown on failure.
		 */
		public final BigDecimal getBigDecimal(int columnIndex, int scale)
			throws SQLException {

			BigDecimal ret = getBigDecimal(columnIndex);
			if (ret != null) {
				return ret.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
			}
			return null;
		}

		public final BigDecimal getBigDecimal(int columnIndex)
			throws SQLException {

			try {

				DataValueDescriptor dvd = getColumn(columnIndex);

				if (wasNull = dvd.isNull())
					return null;
				
				return org.apache.derby.iapi.types.SQLDecimal.getBigDecimal(dvd);

			} catch (StandardException t) {
				throw noStateChangeException(t);
			}
		}

		/**
		 * Get the value of a column in the current row as a java.lang.BigDecimal object.
		 *
		 * @param columnName is the SQL name of the column
		 * @param scale the number of digits to the right of the decimal
		 * @return the column value; if the value is SQL NULL, the result is null
		 * @exception SQLException thrown on failure.
		 */
		public final BigDecimal getBigDecimal(String columnName, int scale)
			throws SQLException {
			return (getBigDecimal(findColumnName(columnName), scale));
		}


    /**
     * JDBC 2.0
     *
		Deprecated in JDBC 2.0, not supported by JCC.
	 * @exception SQLException thrown on failure.
     */
    public final java.io.InputStream getUnicodeStream(int columnIndex) throws SQLException {
		throw Util.notImplemented("getUnicodeStream");
	}
    /**
		Deprecated in JDBC 2.0, not supported by JCC.
	 * @exception SQLException thrown on failure.
     */
    public final java.io.InputStream getUnicodeStream(String columnName) throws SQLException {
		throw Util.notImplemented("getUnicodeStream");
	}	
	
    /**
     * JDBC 2.0
     *
     * Get the value of a column in the current row as a java.math.BigDecimal 
     * object.
     *
         * @exception SQLException Feature not implemented for now.
     */
    public final BigDecimal getBigDecimal(String columnName) throws SQLException {
                        return getBigDecimal(findColumnName(columnName));
        }

    public void updateBigDecimal(int columnIndex, BigDecimal x)
    throws SQLException {
        try {
            getDVDforColumnToBeUpdated(columnIndex, "updateBigDecimal").setValue(x);
        } catch (StandardException t) {
            throw noStateChangeException(t);
        }
    }

	/**
	 * JDBC 2.0
	 * 
	 * Update a column with an Object value.
	 * 
	 * The updateXXX() methods are used to update column values in the current
	 * row, or the insert row. The updateXXX() methods do not update the
	 * underlying database, instead the updateRow() or insertRow() methods are
	 * called to update the database.
	 * 
	 * @param columnIndex
	 *            the first column is 1, the second is 2, ...
	 * @param x
	 *            the new column value
	 * @exception SQLException
	 *                if a database-access error occurs
	 */
	public void updateObject(int columnIndex, Object x) throws SQLException {
		//If the Object x is the right datatype, this method will eventually call getDVDforColumnToBeUpdated which will check for
		//the read only resultset. But for other datatypes of x, we want to catch if this updateObject is being
		//issued against a read only resultset. And that is the reason for call to checksBeforeUpdateOrDelete here.
		checksBeforeUpdateOrDelete("updateObject", columnIndex);
		int colType = getColumnType(columnIndex);

		if (x instanceof BigDecimal) {
			updateBigDecimal(columnIndex, (BigDecimal) x);
			return;
		}
		super.updateObject(columnIndex, x);
	}

    /**
     * JDBC 2.0
     *
     * Update a column with a BigDecimal value.
     *
     * The updateXXX() methods are used to update column values in the
     * current row, or the insert row.  The updateXXX() methods do not 
     * update the underlying database, instead the updateRow() or insertRow()
     * methods are called to update the database.
     *
     * @param columnName the name of the column
     * @param x the new column value
     * @exception SQLException if a database-access error occurs
     */
    public void updateBigDecimal(String columnName, BigDecimal x)
    throws SQLException {
            updateBigDecimal(findColumnName(columnName), x);
        }

    /**
     * JDBC 2.0
     *
     * Returns the value of column @i as a Java object.  Use the
     * param map to determine the class from which to construct data of 
     * SQL structured and distinct types.
     *
     * @param i the first column is 1, the second is 2, ...
     * @param map the mapping from SQL type names to Java classes
     * @return an object representing the SQL value
         * @exception SQLException Feature not implemented for now.
     */
    public Object getObject(int columnIndex, java.util.Map map) throws SQLException {
        if( map == null)
            throw Util.generateCsSQLException(SQLState.INVALID_API_PARAMETER,map,"map",
                                              "java.sql.ResultSet.getObject");
        if(!(map.isEmpty()))
            throw Util.notImplemented();
        // Map is empty call the normal getObject method.
        return getObject(columnIndex);
        }

    /**
     * JDBC 2.0
     *
     * Get a REF(&lt;structured-type&gt;) column.
     *
     * @param i the first column is 1, the second is 2, ...
     * @return an object representing data of an SQL REF type
         * @exception SQLException Feature not implemented for now.
     */
    public Ref getRef(int i) throws SQLException {
                throw Util.notImplemented();
        }

    /**
     * JDBC 2.0
     *
     * Get an array column.
     *
     * @param i the first column is 1, the second is 2, ...
     * @return an object representing an SQL array
         * @exception SQLException Feature not implemented for now.
     */
    public Array getArray(int i) throws SQLException {
                throw Util.notImplemented();
        }

    /**
     * JDBC 2.0
     *
     * Returns the value of column @i as a Java object.  Use the 
     * param map to determine the class from which to construct data of 
     * SQL structured and distinct types.
     *
     * @param colName the column name
     * @param map the mapping from SQL type names to Java classes
     * @return an object representing the SQL value
         * @exception SQLException Feature not implemented for now.
     */
    public Object getObject(String colName, java.util.Map map)
    throws SQLException {
        return getObject(findColumn(colName),map);
        }

    /**
     * JDBC 2.0
     *
     * Get a REF(&lt;structured-type&gt;) column.
     *
     * @param colName the column name
     * @return an object representing data of an SQL REF type
         * @exception SQLException Feature not implemented for now.
     */
    public Ref getRef(String colName) throws SQLException {
                throw Util.notImplemented();
        }




    /**
     * JDBC 2.0
     *
     * Get an array column.
     *
     * @param colName the column name
     * @return an object representing an SQL array
         * @exception SQLException Feature not implemented for now.
     */
    public Array getArray(String colName) throws SQLException {
                throw Util.notImplemented();
        }


        /**
    Following methods are for the new JDBC 3.0 methods in java.sql.ResultSet
    (see the JDBC 3.0 spec). We have the JDBC 3.0 methods in Local20
    package, so we don't have to have a new class in Local30.
    The new JDBC 3.0 methods don't make use of any new JDBC3.0 classes and
    so this will work fine in jdbc2.0 configuration.
        */

        /////////////////////////////////////////////////////////////////////////
        //
        //      JDBC 3.0        -       New public methods
        //
        /////////////////////////////////////////////////////////////////////////



        /**
    * JDBC 3.0
    *
    * Updates the designated column with a java.sql.Ref value. The updater methods are
    * used to update column values in the current row or the insert row. The
    * updater methods do not update the underlying database; instead the updateRow
    * or insertRow methods are called to update the database.
    *
    * @param columnIndex - the first column is 1, the second is 2
    * @param x - the new column value
    * @exception SQLException Feature not implemented for now.
        */
        public void updateRef(int columnIndex, Ref x)
    throws SQLException
        {
                throw Util.notImplemented();
        }

        /**
    * JDBC 3.0
    *
    * Updates the designated column with a java.sql.Ref value. The updater methods are
    * used to update column values in the current row or the insert row. The
    * updater methods do not update the underlying database; instead the updateRow
    * or insertRow methods are called to update the database.
    *
    * @param columnName - the SQL name of the column
    * @param x - the new column value
    * @exception SQLException Feature not implemented for now.
        */
        public void updateRef(String columnName, Ref x)
    throws SQLException
        {
                throw Util.notImplemented();
        }

        /**
    * JDBC 3.0
    *
    * Updates the designated column with a java.sql.Array value. The updater methods are
    * used to update column values in the current row or the insert row. The
    * updater methods do not update the underlying database; instead the updateRow
    * or insertRow methods are called to update the database.
    *
    * @param columnIndex - the first column is 1, the second is 2
    * @param x - the new column value
    * @exception SQLException Feature not implemented for now.
        */
        public void updateArray(int columnIndex, Array x)
    throws SQLException
        {
                throw Util.notImplemented();
        }

        /**
    * JDBC 3.0
    *
    * Updates the designated column with a java.sql.Array value. The updater methods are
    * used to update column values in the current row or the insert row. The
    * updater methods do not update the underlying database; instead the updateRow
    * or insertRow methods are called to update the database.
    *
    * @param columnName - the SQL name of the column
    * @param x - the new column value
    * @exception SQLException Feature not implemented for now.
        */
        public void updateArray(String columnName, Array x)
    throws SQLException
        {
                throw Util.notImplemented();
        }


		@Override
		public RowId getRowId(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public RowId getRowId(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void updateRowId(int columnIndex, RowId x) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateRowId(String columnLabel, RowId x) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public void updateNString(int columnIndex, String nString) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNString(String columnLabel, String nString) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public NClob getNClob(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public NClob getNClob(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public SQLXML getSQLXML(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public SQLXML getSQLXML(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public String getNString(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String getNString(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Reader getNCharacterStream(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Reader getNCharacterStream(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateClob(int columnIndex, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateClob(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNClob(int columnIndex, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void updateNClob(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}


 

}
