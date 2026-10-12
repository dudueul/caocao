using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class DetachChildren : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private static void DoDetachChildren(GameObject go)
		{
		}
	}
}
