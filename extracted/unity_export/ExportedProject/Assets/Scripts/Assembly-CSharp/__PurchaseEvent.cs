using System;
using System.Collections.Generic;

[Serializable]
public class __PurchaseEvent
{
	public int id;

	public string title;

	public string desc;

	public string startDate;

	public string endDate;

	public string currencyCode;

	public int totalPrice;

	public int country;

	public List<string> slotName;

	public List<int> itemId;

	public List<int> price;

	public List<string> priceString;

	public List<int> cashShopID;

	public void CopyTo(PurchaseEvent dest)
	{
	}

	public void Reset()
	{
	}
}
