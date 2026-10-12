using System.Collections.Generic;
using UnityEngine;

namespace TSI
{
	public class TSIEvent : MonoBehaviour
	{
		private Dictionary<TSIRequestType, ITSIListenerType> m_listeners;

		public bool AddListener(TSIRequestType type, ITSIListenerType listener)
		{
			return false;
		}

		public void OnResult(string data)
		{
		}
	}
}
