using System;
using System.Collections.Generic;

namespace com.adjust.sdk
{
	public class AdjustEvent
	{
		internal string currency;

		internal string eventToken;

		internal List<string> partnerList;

		internal List<string> callbackList;

		internal double? revenue;

		internal string receipt;

		internal string transactionId;

		internal bool isReceiptSet;

		public AdjustEvent(string eventToken)
		{
		}

		public void setRevenue(double amount, string currency)
		{
		}

		public void addCallbackParameter(string key, string value)
		{
		}

		public void addPartnerParameter(string key, string value)
		{
		}

		public void setTransactionId(string transactionId)
		{
		}

		[Obsolete]
		public void setReceipt(string receipt, string transactionId)
		{
		}
	}
}
