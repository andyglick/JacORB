/*
 *        JacORB - a free Java ORB
 *
 *   Copyright (C) 1999-2011 Gerald Brose / The JacORB Team.
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Library General Public
 *   License as published by the Free Software Foundation; either
 *   version 2 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this library; if not, write to the Free
 *   Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */
package org.jacorb.ir.gui.remoteobject;

import org.jacorb.ir.gui.typesystem.ModelBuilder;
import org.jacorb.ir.gui.typesystem.TypeAssociator;
import org.jacorb.ir.gui.typesystem.TypeSystemNode;
import org.jacorb.ir.gui.typesystem.remote.IRInterface;
import org.jacorb.ir.gui.typesystem.remote.IRStruct;
import org.jacorb.ir.gui.typesystem.remote.RemoteTypeSystem;
import org.omg.CORBA.Any;
import org.omg.CORBA.InterfaceDef;
import org.omg.CORBA.InterfaceDefHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.PrimitiveDef;
import org.omg.CORBA.PrimitiveKind;
import org.omg.CORBA.Repository;
import org.omg.CORBA.RepositoryHelper;
import org.omg.CORBA.TCKind;

/**
 * This class was generated by a SmartGuide.
 *
 */
public class ObjectRepresentantFactory
{
    private static ORB orb = org.omg.CORBA.ORBSingleton.init();

    /**
     * This method was created by a SmartGuide.
     * @return org.jacorb.ir;.gui.remoteobject.ObjectRepresentant
     * @param counterPart java.lang.Object
     * @param type TypeSystemNode
     * @param referencedBy TypeAssociator: das Attribut, das das Objekt enthält (darf null sein)
     */
    public static ObjectRepresentant create(java.lang.Object counterPart,
                                            TypeSystemNode type,
                                            TypeAssociator referencedBy)
    {
        // referencedBy wird benötigt, falls wir den Inhalt eines Attributes darstellen sollen.
        // Wenn wir this sind, dann gibt es kein referencedBy, aber immer noch einen type (den wir auch brauchen)
        String name = null;
        if (referencedBy!=null) {
            name = ((TypeSystemNode)referencedBy).getName();
        }

        if (type instanceof IRInterface) {
            return new RemoteObject((org.omg.CORBA.Object)counterPart, type, name);
        }
        if (type instanceof IRStruct) {
            return new Struct(counterPart, (IRStruct)type, name);
        }
        /*  if (counterPart instanceof TypeCode) {
            return new Struct(counterPart, null, name);
            }
        */
        return new ObjectRepresentant(counterPart, type, name);
    }
    /**
     * This method was created by a SmartGuide.
     * @param counterPart org.omg.CORBA.Object
     */
    public static ObjectRepresentant create(org.omg.CORBA.Object counterPart) {
        TypeSystemNode typeSystemNode =
            (IRInterface)RemoteTypeSystem.createTypeSystemNode(
                InterfaceDefHelper.narrow( counterPart._get_interface_def()));
        return create(counterPart,typeSystemNode,null);
    }
    /**
     * This method was created by a SmartGuide.
     * @param ior java.lang.String
     */
    public static ObjectRepresentant createFromIOR (String ior) {
        org.omg.CORBA.Object obj = orb.string_to_object(ior);
        return create(obj);
    }
    /**
     * This method was created by a SmartGuide.
     */
    protected static void insertFromString (Any result, String value, TCKind kind ) {
        switch (kind.value()) {
            case TCKind._tk_null:
            case TCKind._tk_void:
            case TCKind._tk_short:
            result.insert_short(Short.parseShort(value));
            break;
            case TCKind._tk_long:
            result.insert_long(Integer.parseInt(value));
            break;
            case TCKind._tk_ushort:
            result.insert_ushort(Short.parseShort(value));
            break;
            case TCKind._tk_ulong:
            result.insert_ulong(Integer.parseInt(value));
            break;
            case TCKind._tk_float:
            result.insert_float(new Float(value).floatValue());
            break;
            case TCKind._tk_double:
            result.insert_double(new Double(value).doubleValue());
            break;
            case TCKind._tk_boolean:
            result.insert_boolean(new Boolean(value).booleanValue());
            break;
            case TCKind._tk_char:
            result.insert_char(value.charAt(0));
            break;
            case TCKind._tk_octet:
            result.insert_octet(new Byte(value).byteValue());
            break;
            /*  case TCKind._tk_any:
                return any.insert_any();
                case TCKind._tk_TypeCode:
                return any.insert_TypeCode();
                case TCKind._tk_Principal:
                return any.insert_Principal();
            */
            case TCKind._tk_objref:
            result.insert_Object(orb.string_to_object(value));
            break;
            /*  case TCKind._tk_struct:
                return any.insert_struct();  // gibt's nicht im Standard-Mapping
            */
            /*  case TCKind._tk_union:
                return any.insert_union();  // gibt's nicht im Standard-Mapping
            */
            case TCKind._tk_enum:
            result.insert_long(Integer.parseInt(value));  // ob das wohl konform ist?
            break;
            case TCKind._tk_string:
            result.insert_string(value);
            break;
            /*  case TCKind._tk_sequence:
                return any.insert_sequence();  // gibt's nicht im Standard-Mapping
            */
            /*  case TCKind._tk_array:
                return any.insert_array();  // gibt's nicht im Standard-Mapping
            */
            /*  case TCKind._tk_except:
                return any.insert_except();  // gibt's nicht im Standard-Mapping
            */
            case TCKind._tk_longlong:
            result.insert_longlong(Long.parseLong(value));
            break;
            case TCKind._tk_ulonglong:
            result.insert_ulonglong(Long.parseLong(value));
            break;
            case TCKind._tk_wchar:
            result.insert_wchar(value.charAt(0));
            break;
            case TCKind._tk_wstring:
            result.insert_wstring(value);
            break;
            default:
        }
    }
    /**
     * This method was created by a SmartGuide.
     * @param args java.lang.String[]
     */
    public static void main(String args[]) {
        try {
            Repository rep    = RepositoryHelper.narrow( orb.resolve_initial_references("InterfaceRepository"));
            PrimitiveDef prim  = rep.get_primitive(PrimitiveKind.pk_short);

            InterfaceDef intf = InterfaceDefHelper.narrow( prim._get_interface_def());
            ObjectRepresentant objr = ObjectRepresentantFactory.create(prim,RemoteTypeSystem.createTypeSystemNode(intf),null);
            ModelBuilder.getSingleton().buildTreeModelAsync(objr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    /**
     * This method was created by a SmartGuide.
     * @return java.lang.Object
     * @param any org.omg.CORBA.Any
     */
    public static java.lang.Object objectFromAny (Any any ) {
        switch (any.type().kind().value()) {
            case TCKind._tk_null:
            return null;
            case TCKind._tk_void:
            System.out.println("DII returned an Any of kind void?");
            return null;
            case TCKind._tk_short:
            return new Short(any.extract_short());
            case TCKind._tk_long:
            return new Integer(any.extract_long());
            case TCKind._tk_ushort:
            return new Short(any.extract_ushort());
            case TCKind._tk_ulong:
            return new Integer(any.extract_ulong());
            case TCKind._tk_float:
            return new Float(any.extract_float());
            case TCKind._tk_double:
            return new Double(any.extract_double());
            case TCKind._tk_boolean:
            return new Boolean(any.extract_boolean());
            case TCKind._tk_char:
            return new Character(any.extract_char());
            case TCKind._tk_octet:
            return new Byte(any.extract_octet());
            case TCKind._tk_any:
            return any.extract_any();
            case TCKind._tk_TypeCode:
            return any.extract_TypeCode();
            case TCKind._tk_Principal:
            return any.extract_Principal();
            case TCKind._tk_objref:
            return any.extract_Object();
            /*  case TCKind._tk_struct:
                return any.extract_struct();  // gibt's nicht im Standard-Mapping
            */
            /*  case TCKind._tk_union:
                return any.extract_union();  // gibt's nicht im Standard-Mapping
            */
            /*  case TCKind._tk_enum:
                return any.extract_enum();  // gibt's nicht im Standard-Mapping
            */
            case TCKind._tk_string:
            return any.extract_string();
            /*  case TCKind._tk_sequence:
                return any.extract_sequence();  // gibt's nicht im Standard-Mapping
                case TCKind._tk_array:
                return any.extract_array();  // gibt's nicht im Standard-Mapping                 case TCKind._tk_except:
                return any.extract_except();  // gibt's nicht im Standard-Mapping
            */
            case TCKind._tk_longlong:
            return new Long(any.extract_longlong());
            case TCKind._tk_ulonglong:
            return new Long(any.extract_ulonglong());
            case TCKind._tk_wchar:
            return new Character(any.extract_wchar());
            case TCKind._tk_wstring:
            return new String(any.extract_wstring());
        }
        return null;
    }
}











