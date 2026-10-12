namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dSpriteSetScale : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 scale;

		public bool everyframe;

		private tk2dBaseSprite _sprite;

		private void _getSprite()
		{
		}

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetSpriteScale()
		{
		}
	}
}
