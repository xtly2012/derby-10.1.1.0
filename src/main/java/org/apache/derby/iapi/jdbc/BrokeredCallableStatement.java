/*

   Derby - Class org.apache.derby.iapi.jdbc.BrokeredCallableStatement

   Copyright 2003, 2004 The Apache Software Foundation or its licensors, as applicable.

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

package org.apache.derby.iapi.jdbc;

import java.sql.*;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;


/**
	JDBC 2 brokered CallableStatement
 */
public class BrokeredCallableStatement extends BrokeredPreparedStatement
          implements CallableStatement
{

	public BrokeredCallableStatement(BrokeredStatementControl control, int jdbcLevel, String sql) throws SQLException {
		super(control, jdbcLevel, sql);
	}

    public final void registerOutParameter(int parameterIndex,
                                     int sqlType)
        throws SQLException
    {
        getCallableStatement().registerOutParameter( parameterIndex, sqlType);
    }

    public final void registerOutParameter(int parameterIndex,
                                     int sqlType,
                                     int scale)
        throws SQLException
    {
        getCallableStatement().registerOutParameter( parameterIndex, sqlType, scale);
    }

    public final boolean wasNull()
        throws SQLException
    {
        return getCallableStatement().wasNull();
    }

    public final String getString(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getString( parameterIndex);
    }

    public final boolean getBoolean(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getBoolean( parameterIndex);
    }

    public final byte getByte(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getByte( parameterIndex);
    }

    public final short getShort(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getShort( parameterIndex);
    }

    public final int getInt(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getInt( parameterIndex);
    }

    public final long getLong(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getLong( parameterIndex);
    }

    public final float getFloat(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getFloat( parameterIndex);
    }

    public final double getDouble(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getDouble( parameterIndex);
    }

    public final BigDecimal getBigDecimal(int parameterIndex,
                                              int scale)
        throws SQLException
    {
        return getCallableStatement().getBigDecimal( parameterIndex, scale);
    }

    public final byte[] getBytes(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getBytes( parameterIndex);
    }

    public final Date getDate(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getDate( parameterIndex);
    }

    public final Date getDate(int parameterIndex,
                        Calendar cal)
        throws SQLException
    {
        return getCallableStatement().getDate( parameterIndex, cal);
    }

    public final Time getTime(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getTime( parameterIndex);
    }

    public final Timestamp getTimestamp(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getTimestamp( parameterIndex);
    }

    public final Object getObject(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getObject( parameterIndex);
    }

    public final BigDecimal getBigDecimal(int parameterIndex)
        throws SQLException
    {
        return getCallableStatement().getBigDecimal( parameterIndex);
    }

    public final Object getObject(int i,
                            Map map)
        throws SQLException
    {
        return getCallableStatement().getObject( i, map);
    }

    public final Ref getRef(int i)
        throws SQLException
    {
        return getCallableStatement().getRef( i);
    }

    public final Blob getBlob(int i)
        throws SQLException
    {
        return getCallableStatement().getBlob( i);
    }

    public final Clob getClob(int i)
        throws SQLException
    {
        return getCallableStatement().getClob( i);
    }

    public final Array getArray(int i)
        throws SQLException
    {
        return getCallableStatement().getArray( i);
    }

    public final Time getTime(int parameterIndex,
                        Calendar cal)
        throws SQLException
    {
        return getCallableStatement().getTime( parameterIndex, cal);
    }

    public final Timestamp getTimestamp(int parameterIndex,
                                  Calendar cal)
        throws SQLException
    {
        return getCallableStatement().getTimestamp( parameterIndex, cal);
    }

    public final void registerOutParameter(int paramIndex,
                                     int sqlType,
                                     String typeName)
        throws SQLException
    {
        getCallableStatement().registerOutParameter( paramIndex, sqlType, typeName);
    }

	/*
	** Control methods
	*/

	protected final CallableStatement getCallableStatement() throws SQLException {
		return control.getRealCallableStatement();
	}
	public final PreparedStatement getPreparedStatement() throws SQLException {
		return getCallableStatement();
	}
	/**
		Create a duplicate CalableStatement to this, including state, from the passed in Connection.
	*/
	public CallableStatement createDuplicateStatement(Connection conn, CallableStatement oldStatement) throws SQLException {

		CallableStatement newStatement = conn.prepareCall(sql, resultSetType, resultSetConcurrency);

		setStatementState(oldStatement, newStatement);

		return newStatement;
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public URL getURL(int parameterIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setURL(String parameterName, URL val) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNull(String parameterName, int sqlType) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBoolean(String parameterName, boolean x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setByte(String parameterName, byte x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShort(String parameterName, short x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInt(String parameterName, int x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLong(String parameterName, long x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFloat(String parameterName, float x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDouble(String parameterName, double x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setString(String parameterName, String x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBytes(String parameterName, byte[] x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDate(String parameterName, Date x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTime(String parameterName, Time x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObject(String parameterName, Object x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getString(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getBoolean(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte getByte(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short getShort(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloat(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getBytes(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ref getRef(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob getBlob(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob getClob(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array getArray(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getURL(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowId getRowId(int parameterIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowId getRowId(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRowId(String parameterName, RowId x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNString(String parameterName, String value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNClob(String parameterName, NClob value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClob(String parameterName, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NClob getNClob(int parameterIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob getNClob(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML getSQLXML(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNString(int parameterIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNString(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getNCharacterStream(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getCharacterStream(String parameterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBlob(String parameterName, Blob x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClob(String parameterName, Clob x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClob(String parameterName, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNClob(String parameterName, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
