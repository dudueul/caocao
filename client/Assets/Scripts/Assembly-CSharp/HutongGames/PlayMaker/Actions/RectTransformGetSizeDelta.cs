using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformGetSizeDelta : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 sizeDelta;

		public FsmFloat width;

		public FsmFloat height;

		private RectTransform _rt;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoGetValues()
		{
		}
	}
}
