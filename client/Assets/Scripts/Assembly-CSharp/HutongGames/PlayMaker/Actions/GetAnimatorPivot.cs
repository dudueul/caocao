using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorPivot : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat pivotWeight;

		public FsmVector3 pivotPosition;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoCheckPivot()
		{
		}
	}
}
