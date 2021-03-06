/*************************************************************************
 * Copyright 2009-2012 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 *
 * This file may incorporate work covered under the following copyright
 * and permission notice:
 *
 *   Software License Agreement (BSD License)
 *
 *   Copyright (c) 2008, Regents of the University of California
 *   All rights reserved.
 *
 *   Redistribution and use of this software in source and binary forms,
 *   with or without modification, are permitted provided that the
 *   following conditions are met:
 *
 *     Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *     Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer
 *     in the documentation and/or other materials provided with the
 *     distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *   "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *   LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *   FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *   COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *   BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *   CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *   ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *   POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 *   THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 *   COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 *   AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 *   IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 *   SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 *   WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 *   REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 *   IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 *   NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

package com.eucalyptus.reporting.modules.instance;

import com.eucalyptus.reporting.ReportLine;
import com.eucalyptus.reporting.units.*;

public class InstanceReportLine
	implements Comparable<InstanceReportLine>, ReportLine
{
	private static final Units INTERNAL_UNITS =
		new Units(TimeUnit.SECS, SizeUnit.MB, TimeUnit.SECS, SizeUnit.MB);
	
	private final InstanceReportLineKey key;
	private final InstanceUsageSummary summary;
	private final Units units;

	InstanceReportLine(InstanceReportLineKey key,
			InstanceUsageSummary summary, Units units)
	{
		this.key = key;
		this.summary = summary;
		this.units = units;
	}

	public String getLabel()
	{
		return key.getLabel();
	}

	public String getGroupBy()
	{
		return key.getGroupByLabel();
	}

	public Long getM1SmallNum()
	{
		return summary.getM1SmallNum();
	}

	public Long getM1SmallTime()
	{
		return UnitUtil.convertTime(summary.getM1SmallTimeSecs(),
				INTERNAL_UNITS.getTimeUnit(), units.getTimeUnit());
	}

	public Long getC1MediumNum()
	{
		return summary.getC1MediumNum();
	}

	public Long getC1MediumTime()
	{
		return UnitUtil.convertTime(summary.getC1MediumTimeSecs(),
				INTERNAL_UNITS.getTimeUnit(), units.getTimeUnit());
	}

	public Long getM1LargeNum()
	{
		return summary.getM1LargeNum();
	}

	public Long getM1LargeTime()
	{
		return UnitUtil.convertTime(summary.getM1LargeTimeSecs(),
				INTERNAL_UNITS.getTimeUnit(), units.getTimeUnit());
	}

	public Long getM1XLargeNum()
	{
		return summary.getM1XLargeNum();
	}

	public Long getM1XLargeTime()
	{
		return UnitUtil.convertTime(summary.getM1XLargeTimeSecs(),
				INTERNAL_UNITS.getTimeUnit(), units.getTimeUnit());
	}

	public Long getC1XLargeNum()
	{
		return summary.getC1XLargeNum();
	}

	public Long getC1XLargeTime()
	{
		return UnitUtil.convertTime(summary.getC1XLargeTimeSecs(),
				INTERNAL_UNITS.getTimeUnit(), units.getTimeUnit());
	}

	public Long getNetworkIoSize()
	{
		return UnitUtil.convertSize(summary.getNetworkIoMegs(),
				INTERNAL_UNITS.getSizeUnit(), units.getSizeUnit());
	}

	public Long getDiskIoSize()
	{
		return UnitUtil.convertSize(summary.getDiskIoMegs(),
				INTERNAL_UNITS.getSizeUnit(), units.getSizeUnit());
	}
	
	public Units getUnits()
	{
		return units;
	}
	
	void addUsage(InstanceUsageSummary summary)
	{
		this.summary.addUsage(summary);
	}
	
	public String toString()
	{
		return String.format("[key:%s,summary:%s]", this.key, this.summary);
	}
	
	@Override
	public int compareTo(InstanceReportLine other)
	{
		return key.compareTo(other.key);
	}


}
