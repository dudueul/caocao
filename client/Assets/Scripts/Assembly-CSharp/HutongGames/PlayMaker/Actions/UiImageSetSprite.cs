using UnityEngine;
using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiImageSetSprite : ComponentAction<Image>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmObject sprite;

		public FsmBool resetOnExit;

		private Image image;

		private Sprite originalSprite;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetImageSourceValue()
		{
		}

		public override void OnExit()
		{
		}
	}
}
