using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiImageGetSprite : ComponentAction<Image>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmObject sprite;

		private Image image;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetImageSourceValue()
		{
		}
	}
}
