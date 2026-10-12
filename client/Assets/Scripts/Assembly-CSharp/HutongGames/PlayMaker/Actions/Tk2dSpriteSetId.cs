namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dSpriteSetId : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt spriteID;

		public FsmString ORSpriteName;

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

		private void DoSetSpriteID()
		{
		}
	}
}
