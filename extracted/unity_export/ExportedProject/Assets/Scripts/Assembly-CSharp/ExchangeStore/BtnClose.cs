using System;
using UnityEngine;

namespace ExchangeStore
{
	public class BtnClose : IDisposable
	{
		private UIButton m_btnClose;

		private GameObject m_exMark;

		public BtnClose(Transform transform)
		{
		}

		public void SetOnClickHandler(MonoBehaviour behaviour, string methodName)
		{
		}

		public void Dispose()
		{
		}
	}
}
