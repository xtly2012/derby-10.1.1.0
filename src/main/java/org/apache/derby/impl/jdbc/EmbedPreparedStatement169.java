/*

Derby - Class org.apache.derby.impl.jdbc.EmbedPreparedStatement169

Copyright 2005 The Apache Software Foundation or its licensors, as applicable.

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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;

/**
 * PreparedStatement implementation for JSR169.
 * Adds no functionality to its (abstract) parent class.
 * If Derby could be compiled against JSR169 that the parent
 * class could be the concrete class for the environment.
 * Just like for the JDBC 2.0 specific classes.
 * Until that is possible (ie. easily downloadable J2ME/CDC/Foundation/JSR169
 * jar files, this class is required and is only compiled by an optional target.
 <P><B>Supports</B>
 <UL>
 <LI> JSR 169
 </UL>

 */

public final class EmbedPreparedStatement169 extends EmbedPreparedStatement
{
	public EmbedPreparedStatement169(EmbedConnection conn, String sql,
			boolean forMetaData, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability, int autoGeneratedKeys,
			int[] columnIndexes, String[] columnNames) throws SQLException {

		super(conn, sql, forMetaData, resultSetType, resultSetConcurrency,
				resultSetHoldability, autoGeneratedKeys, columnIndexes,
				columnNames);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
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
}