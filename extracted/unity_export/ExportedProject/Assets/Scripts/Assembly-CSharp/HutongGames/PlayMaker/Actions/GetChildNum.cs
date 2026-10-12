using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetChildNum : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt childIndex;

		[RequiredField]
		public FsmGameObject store;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private GameObject DoGetChildNum(GameObject go)
		{
			return null;
		}
	}
}
