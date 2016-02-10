package native
package nir

import Type._

object Intrinsic {
  private def value(id: String, ty: Type) =
    Val.Global(Global.Intrinsic(id), ty)
  private def intrinsic(id: String, args: Seq[Type], ret: Type) =
    Val.Global(Global.Intrinsic(id), Type.Ptr(Type.Function(args, ret)))
  private def nullary(id: String, to: Type) =
    intrinsic(id, Seq(), to)
  private def unary(id: String, from: Type, to: Type) =
    intrinsic(id, Seq(from), to)
  private def binary(id: String, from1: Type, from2: Type, to: Type) =
    intrinsic(id, Seq(from1, from2), to)
  private def ternary(id: String, from1: Type, from2: Type, from3: Type, to: Type) =
    intrinsic(id, Seq(from1, from2, from3), to)
  private def cls(id: String) =
    Type.Class(Global.Intrinsic(id))

  val prim_box = Map[Type, Val.Global](
    BooleanClass   -> unary("bool_box"  , Bool, BooleanClass  ),
    CharacterClass -> unary("char_box"  , I16 , CharacterClass),
    ByteClass      -> unary("byte_box"  , I8  , ByteClass     ),
    ShortClass     -> unary("short_box" , I16 , ShortClass    ),
    IntegerClass   -> unary("int_box"   , I32 , IntegerClass  ),
    LongClass      -> unary("long_box"  , I64 , IntegerClass  ),
    FloatClass     -> unary("float_box" , F32 , FloatClass    ),
    DoubleClass    -> unary("double_box", F64 , DoubleClass   )
  )

  val prim_unbox = Map[Type, Val.Global](
    BooleanClass   -> unary("bool_unbox"  , BooleanClass  , Bool),
    CharacterClass -> unary("char_unbox"  , CharacterClass, I16 ),
    ByteClass      -> unary("byte_unbox"  , ByteClass     , I8  ),
    ShortClass     -> unary("short_unbox" , ShortClass    , I16 ),
    IntegerClass   -> unary("int_unbox"   , IntegerClass  , I32 ),
    LongClass      -> unary("long_unbox"  , IntegerClass  , I64 ),
    FloatClass     -> unary("float_unbox" , FloatClass    , F32 ),
    DoubleClass    -> unary("double_unbox", DoubleClass   , F64 )
  )

  val prim_to_string = Map[Type, Val.Global](
    BooleanClass   -> unary("bool_to_string"  , Bool, StringClass),
    CharacterClass -> unary("char_to_string"  , I16 , StringClass),
    ByteClass      -> unary("byte_to_string"  , I8  , StringClass),
    ShortClass     -> unary("short_to_string" , I16 , StringClass),
    IntegerClass   -> unary("int_to_string"   , I32 , StringClass),
    LongClass      -> unary("long_to_string"  , I64 , StringClass),
    FloatClass     -> unary("float_to_string" , F32 , StringClass),
    DoubleClass    -> unary("double_to_string", F64 , StringClass)
  )

  val prim_to_unsigned_string = Map[Type, Val.Global](
    IntegerClass   -> unary("int_to_unsigned_string" , I32, StringClass),
    LongClass      -> unary("long_to_unsigned_string", I64, StringClass)
  )

  val prim_to_string_rdx = Map[Type, Val.Global](
    IntegerClass   -> binary("int_to_string_rdx" , I32, I32, StringClass),
    LongClass      -> binary("long_to_string_rdx", I64, I32, StringClass)
  )

  val prim_to_unsigned_string_rdx = Map[Type, Val.Global](
    IntegerClass   -> binary("int_to_unsigned_string_rdx" , I32, I32, StringClass),
    LongClass      -> binary("long_to_unsigned_string_rdx", I64, I32, StringClass)
  )

  val prim_parse = Map[Type, Val.Global](
    BooleanClass   -> unary("bool_parse"  , Bool, StringClass),
    ByteClass      -> unary("byte_parse"  , I8  , StringClass),
    ShortClass     -> unary("short_parse" , I16 , StringClass),
    IntegerClass   -> unary("int_parse"   , I32 , StringClass),
    LongClass      -> unary("long_parse"  , I64 , StringClass),
    FloatClass     -> unary("float_parse" , F32 , StringClass),
    DoubleClass    -> unary("double_parse", F64 , StringClass)
  )

  val prim_parse_unsigned = Map[Type, Val.Global](
    IntegerClass   -> unary("int_parse_unsigned" , I32, StringClass),
    LongClass      -> unary("long_parse_unsigned", I64, StringClass)
  )

  val prim_parse_rdx = Map[Type, Val.Global](
    ByteClass      -> binary("byte_parse_rdx" , I8 , I32, StringClass),
    ShortClass     -> binary("short_parse_rdx", I16, I32, StringClass),
    IntegerClass   -> binary("int_parse_rdx"  , I32, I32, StringClass),
    LongClass      -> binary("long_parse_rdx" , I64, I32, StringClass)
  )

  val prim_parse_unsigned_rdx = Map[Type, Val.Global](
    IntegerClass   -> binary("int_parse_unsigned_rdx" , I32, I32, StringClass),
    LongClass      -> binary("long_parse_unsigned_rdx", I64, I32, StringClass)
  )

  val prim_hash_code = Map[Type, Val.Global](
    BooleanClass   -> unary("bool_hash_code"  , Bool, I32),
    CharacterClass -> unary("char_hash_code"  , I16 , I32),
    ByteClass      -> unary("byte_hash_code"  , I8  , I32),
    ShortClass     -> unary("short_hash_code" , I16 , I32),
    IntegerClass   -> unary("int_hash_code"   , I32 , I32),
    LongClass      -> unary("long_hash_code"  , I64 , I32),
    FloatClass     -> unary("float_hash_code" , F32 , I32),
    DoubleClass    -> unary("double_hash_code", F64 , I32)
  )

  val builtin_class = Map[Type.BuiltinClassKind, Val.Global](
    NullClass      -> value("null_class"  , ClassClass),
    ObjectClass    -> value("object_class", ClassClass),
    ClassClass     -> value("class_class" , ClassClass),
    StringClass    -> value("string_class", ClassClass),
    CharacterClass -> value("char_class"  , ClassClass),
    BooleanClass   -> value("bool_class"  , ClassClass),
    ByteClass      -> value("byte_class"  , ClassClass),
    ShortClass     -> value("short_class" , ClassClass),
    IntegerClass   -> value("int_class"   , ClassClass),
    LongClass      -> value("long_class"  , ClassClass),
    FloatClass     -> value("float_class" , ClassClass),
    DoubleClass    -> value("double_class", ClassClass)
  )

  val object_          = cls   ("object")
  val object_equals    = binary("object_equals"   , ObjectClass, ObjectClass, Bool       )
  val object_to_string = unary ("object_to_string", ObjectClass,              StringClass)
  val object_hash_code = unary ("object_hash_code", ObjectClass,              I32        )
  val object_get_class = unary ("object_get_class", ObjectClass,              ClassClass )

  val alloc = binary("alloc", ClassClass, Size, ObjectClass)

  val class_get_name = unary("class_get_name", ClassClass, StringClass)
  val class_get_size = unary("class_get_size", ClassClass, Size       )

  val monitor_enter      = unary  ("monitor_enter"     , ObjectClass,           Unit)
  val monitor_exit       = unary  ("monitor_exit"      , ObjectClass,           Unit)
  val monitor_notify     = unary  ("monitor_notify"    , ObjectClass,           Unit)
  val monitor_notify_all = unary  ("monitor_notify_all", ObjectClass,           Unit)
  val monitor_wait       = ternary("monitor_wait"      , ObjectClass, I64, I32, Unit)

  val string_concat   = binary("string_concat",   StringClass,  StringClass, StringClass)
  val string_from_ptr = binary("string_from_ptr", Type.Ptr(I8), I32,         StringClass)

  val init   = binary ("init",  Type.I32, Type.Ptr(Type.Ptr(Type.I8)), ArrayClass(StringClass))
  val yield_ = nullary("yield",                                        Unit                   )

  def call(intr: Val.Global, args: Val*): Op = {
    val Val.Global(_, Type.Ptr(ty)) = intr
    Op.Call(ty, intr, args)
  }
}