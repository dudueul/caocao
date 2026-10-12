using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiImageSetFillAmount : ComponentAction<Image>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat ImageFillAmount;

		public bool everyFrame;

		private Image image;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetFillAmount()
		{
		}
	}
}
