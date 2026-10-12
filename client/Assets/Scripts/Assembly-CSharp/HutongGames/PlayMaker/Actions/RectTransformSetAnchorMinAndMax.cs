using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformSetAnchorMinAndMax : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 anchorMax;

		public FsmVector2 anchorMin;

		public FsmFloat xMax;

		public FsmFloat yMax;

		public FsmFloat xMin;

		public FsmFloat yMin;

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

		private void DoSetAnchorMax()
		{
		}
	}
}
