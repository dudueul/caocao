using System;
using UnityEngine;

namespace WorldMapMenu
{
	public abstract class PageButtonBase : MonoBehaviour
	{
		public Action OnClickCallback;

		public abstract void OnClickImpl();

		protected virtual void Awake()
		{
		}

		protected virtual void OnClickEvent()
		{
		}

		protected void DeactivateGOForFullScreenPanelUI()
		{
		}

		protected void ActivateGOForFullScreenPanelUI()
		{
		}
	}
}
