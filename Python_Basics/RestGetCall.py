# -*- coding: utf-8 -*-
"""
Created on Wed Mar 28 16:38:30 2018

@author: syadava
"""

import http.client

conn = http.client.HTTPSConnection("kepler.eng.vmware.com")

payload = "<HistoricUsageSpec>\r\n    <RelativeStartTime interval=\"1\" unit=\"day\"/>\r\n    <RelativeEndTime interval=\"1\" unit=\"day\"/>\r\n    <MetricPattern>cpu.*</MetricPattern>\r\n    <MetricPattern>disk.*</MetricPattern>\r\n</HistoricUsageSpec>"

headers = {
    'accept': "application/*+xml;version=30.0",
    'x-vcloud-authorization': "44cc7aa0e6034b838d22e528157d7961",
    'content-type': "application/vnd.vmware.vcloud.metrics.historicusage+xml;version=30.0",
    'authorization': "Basic YWRtaW5pc3RyYXRvckBzeXN0ZW06Y2EkaGMwdw==",
    'cache-control': "no-cache",
    'postman-token': "657d13fb-1c4f-1bf3-e35c-c570ca29be8d"
    }

conn.request("GET", "/api/org", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))