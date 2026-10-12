using System;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetTagsOnChildren : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString tag;

		public FsmString filterByComponent;

		private Type componentFilter;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void SetTag(GameObject parent)
		{
		}

		private void UpdateComponentFilter()
		{
		}
	}
}
