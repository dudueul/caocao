using System;
using CaoCaoIAB;
using UnityEngine;

public class CaoCaoIABEventManager : MonoBehaviour
{
	private Action billingInitSucceed__BackingField;

	private Action<string> billingInitFailed__BackingField;

	private Action<Inventory> queryInventorySucceed__BackingField;

	private Action<string> queryInventoryFailed__BackingField;

	private Action<Purchase> purchaseSucceed__BackingField;

	private Action<string> purchaseFailed__BackingField;

	private Action<Purchase> consumePurchaseSucceed__BackingField;

	private Action<string> consumePurchaseFailed__BackingField;

	public event Action billingInitSucceed
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<string> billingInitFailed
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<Inventory> queryInventorySucceed
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<string> queryInventoryFailed
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<Purchase> purchaseSucceed
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<string> purchaseFailed
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<Purchase> consumePurchaseSucceed
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<string> consumePurchaseFailed
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Awake()
	{
	}

	private void Destroy()
	{
	}

	public void OnBillingInitSucceed()
	{
	}

	public void OnBillingInitFailed(string err)
	{
	}

	public void OnQueryInventorySucceed(string json)
	{
	}

	public void OnQueryInventoryFailed(string err)
	{
	}

	public void OnPurchaseSucceed(string json)
	{
	}

	public void OnPurchaseFailed(string errMsg)
	{
	}

	public void OnConsumePurchaseSucceed(string json)
	{
	}

	public void OnConsumePurchaseFailed(string err)
	{
	}

	public void OnPurchasesUpdated(string args)
	{
	}
}
