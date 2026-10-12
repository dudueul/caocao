using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

namespace CaocaoLobby
{
	public class Controller : MonoBehaviour
	{
		[SerializeField]
		private Transform blackSprite;

		[SerializeField]
		private Transform unit7;

		[SerializeField]
		private Transform hero;

		[SerializeField]
		private Transform bg;

		[SerializeField]
		private GameObject buttons;

		private Coroutine m_closeCoroutine;

		private Coroutine m_lockTouchCoroutine;

		public static bool forcedLobby;

		public bool isLobbyOn
		{
			get
			{
				return false;
			}
		}

		public bool isTransiting
		{
			get
			{
				return false;
			}
		}

		private void Awake()
		{
		}

		private void CheckRepresentUnitGroup()
		{
		}

		private List<UserUnit> GetSortedLobbyUnits()
		{
			return null;
		}

		public void UpdateLobby()
		{
		}

		private void OnActivateComplete()
		{
		}

		private void OnDestroy()
		{
		}

		[DebuggerHidden]
		private IEnumerator LockTouch(float time)
		{
			return null;
		}

		private void UpdateLobbyUnits()
		{
		}

		public void CloseLobby()
		{
		}

		[DebuggerHidden]
		private IEnumerator CloseLobbyImpl()
		{
			return null;
		}

		public void OnBtnEditUnitList()
		{
		}

		public void OnBtnMyInfo()
		{
		}

		public void OnBtnVisit()
		{
		}
	}
}
